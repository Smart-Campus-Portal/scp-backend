package tut.scp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tut.scp.dto.AuthRequest;
import tut.scp.dto.AuthResponse;
import tut.scp.dto.UserResponse;
import tut.scp.entity.User;
import tut.scp.security.JWTService;
import tut.scp.service.IUserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final IUserService userService;

    @Autowired
    public AuthController(AuthenticationManager authManager, JWTService jwtService, IUserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/complete-registration")
    public ResponseEntity<?> completeRegistration(@RequestBody AuthRequest authRequest) {
        Optional<User> optionalUser = userService.getUserByEmail(authRequest.getEmail());
        if (optionalUser.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User not found");
            ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        return userService.setPassword(optionalUser.get(), authRequest.getPassword());

    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        /* get user info to return with token */
        Optional<User> optionalUser = userService.getUserByEmail(userDetails.getUsername());
        if (optionalUser.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }

        User user = optionalUser.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthResponse(token,
                        UserResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .role(user.getRole().toString())
                        .build()
                        )
                );
    }

}
