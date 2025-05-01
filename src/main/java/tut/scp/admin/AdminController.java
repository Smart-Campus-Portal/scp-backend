package tut.scp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.UserRequest;
import tut.scp.shared.service.IUser;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final IUser userService;

    @Autowired
    public AdminController(IUser userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

}
