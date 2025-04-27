package tut.scp.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.UserRequest;
import tut.scp.entity.User;

import java.util.Optional;

public interface IUserService {
    ResponseEntity<?> setPassword(User user, String password);
    ResponseEntity<?> addUser(UserRequest userRequest);
    Optional<User> getUserByEmail(String email);
}
