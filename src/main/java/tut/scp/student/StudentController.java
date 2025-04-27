package tut.scp.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/view-timetable")
    public ResponseEntity<?> viewTimetable() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Timetable");
    }
}
