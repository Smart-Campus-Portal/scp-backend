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
}
