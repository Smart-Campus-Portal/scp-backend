package tut.scp.shared.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tut.scp.dto.UserRequest;
import tut.scp.entity.User;
import tut.scp.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUser {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
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
        tut.scp.entity.User newUser = new tut.scp.entity.User();
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

    @Override
    public void updateUserPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        user.setEnabled(true);
        userRepo.save(user);
    }

}
