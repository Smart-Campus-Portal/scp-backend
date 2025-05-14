package tut.scp.shared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.UserRequest;
import tut.scp.shared.service.IUser;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final IUser userService;

    @Autowired
    public UserController(IUser userService) {
        this.userService = userService;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserRequest userRequest) {
        return userService.updateUser(userRequest);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return userService.searchUsers(query, page, size);
    }
}
