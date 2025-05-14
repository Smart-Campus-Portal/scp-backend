package tut.scp.shared.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.UserRequest;
import tut.scp.entity.User;

import java.util.Optional;

public interface IUser {
    ResponseEntity<?> addUser(UserRequest userRequest);
    Optional<User> getUserByEmail(String email);
    void updateUserPassword(User user, String newPassword);
    ResponseEntity<?> updateUser(UserRequest userRequest);
    ResponseEntity<?> getUserById(Long id);
    ResponseEntity<?> searchUsers(String query, int page, int size);
}
