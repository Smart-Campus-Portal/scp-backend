package tut.scp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.UserRequest;
import tut.scp.shared.service.IUser;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final IUser userService;
    private final IDashboard dashboardService;

    @Autowired
    public AdminController(IUser userService, IDashboard dashboardService) {
        this.userService = userService;
        this.dashboardService = dashboardService;
    }

=======
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
>>>>>>> lecturer-local/lecturer
    @PostMapping("/add-user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

<<<<<<< HEAD
    @GetMapping("/dashboard/overview")
    public ResponseEntity<?> getOverview() {
        return dashboardService.getOverview();
    }

    @GetMapping("/dashboard/top-booked-study-rooms")
    public ResponseEntity<?> getTopBookedStudyRooms() {
        return dashboardService.getTopBookedStudyRooms();
    }

=======
>>>>>>> lecturer-local/lecturer
}
