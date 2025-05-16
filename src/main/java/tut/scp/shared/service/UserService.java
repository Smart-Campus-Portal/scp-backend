package tut.scp.shared.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tut.scp.dto.PageResponse;
import tut.scp.dto.UserRequest;
import tut.scp.dto.UserResponse;
import tut.scp.entity.User;
import tut.scp.repository.UserRepository;

import java.util.*;

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

    @Override
    public ResponseEntity<?> updateUser(UserRequest userRequest) {
        User user = getUserByEmail(userRequest.getEmail()).get();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        userRepo.save(user);

        log.info("User {} updated", user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("message", "User successfully updated");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        log.info("Retrieving user with id {}", id);
        User user = userRepo.findById(id).get();
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponse);
    }

    @Override
    public ResponseEntity<?> searchUsers(String query, int page, int size) {
        log.info("Searching users with query: {}", query);

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<User> userPage = userRepo.searchUsers(query, pageable);

        if (!userPage.hasContent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No users found with query " + query);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        List<UserResponse> users = userPage.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .role(user.getRole().toString())
                        .build())
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(PageResponse.builder()
                        .content(Collections.singletonList(users))
                        .pageNumber(userPage.getNumber() + 1)
                        .pageSize(userPage.getSize())
                        .totalPages(userPage.getTotalPages())
                        .totalElements(userPage.getTotalElements())
                        .last(userPage.isLast())
                        .build()
                );
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long id) {
        User user = userRepo.findById(id).get();
        userRepo.delete(user);
        log.info("User {} deleted", user.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User successfully deleted");
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
