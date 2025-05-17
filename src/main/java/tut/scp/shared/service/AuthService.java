package tut.scp.shared.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tut.scp.dto.AuthRequest;
import tut.scp.dto.AuthResponse;
import tut.scp.dto.UserResponse;
import tut.scp.entity.User;
import tut.scp.security.JWTService;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class AuthService implements IAuth{

    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final IUser userService;

    @Autowired
    public AuthService(AuthenticationManager authManager, JWTService jwtService, PasswordEncoder passwordEncoder, IUser userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> setPassword(AuthRequest request) {
        try {
            Optional<User> optionalUser = userService.getUserByEmail(request.getEmail());
            Map<String, String> response = new HashMap<>();
            if (optionalUser.isEmpty()) {
                response.put("message", "User not found");
                ResponseEntity.status(HttpStatus.OK)
                        .body(response);
            }

            User user = optionalUser.get();
            if (user.isEnabled()) {
                response.put("message", "User already enabled");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(response);
            }
            log.info("Password update by user: {}", user.getEmail());
            userService.updateUserPassword(user, passwordEncoder.encode(request.getPassword()));

            response.put("message", "Password updated successfully");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);

        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }


    @Override
    public ResponseEntity<?> signIn(AuthRequest authRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            log.info("sign in: {}", authRequest.getEmail());

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            /* get user info to return with a token */
            Optional<User> optionalUser = userService.getUserByEmail(userDetails.getUsername());

            User user = optionalUser.get();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AuthResponse(token,
                                    UserResponse.builder()
                                            .id(user.getId())
                                            .firstName(user.getFirstName())
                                            .lastName(user.getLastName())
                                            .email(user.getEmail())
                                            .role(user.getRole().toString())
                                            .build()
                            )
                    );

        }catch (BadCredentialsException bce) {
            Map<String, String> response = new HashMap<>();
            response.put("message", bce.getMessage());
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(response);

        }catch (AuthenticationException ae) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Authentication failed");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
