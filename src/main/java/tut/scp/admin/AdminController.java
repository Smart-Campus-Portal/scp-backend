package tut.scp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tut.scp.dto.UserRequest;
import tut.scp.service.IUser;

@RestController
@RequestMapping("/api/admin")
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
