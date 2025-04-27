package tut.scp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tut.scp.dto.UserRequest;
import tut.scp.entity.User;
import tut.scp.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> setPassword(User user, String password) {
        Map<String, String> response = new HashMap<>();
        if(user.isEnabled()) {
            response.put("message", "User already enabled");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
        log.info("Password update by user: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        userRepo.save(user);

        response.put("message", "Password updated successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @Override
    public ResponseEntity<?> addUser(UserRequest userRequest) {
        Map<String, String> response = new HashMap<>();
        if (userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
            response.put("message", "User already exists");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
        log.info("Creating new user: {}", userRequest.getEmail());
        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setRole(userRequest.getRole());
        newUser.setEnabled(false);
        userRepo.save(newUser);

        response.put("message", "User successfully created");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email);
    }
}
