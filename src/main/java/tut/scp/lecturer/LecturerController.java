package tut.scp.lecturer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lecturer")
@PreAuthorize("hasRole('ROLE_LECTURER')")
@CrossOrigin(origins = "http://localhost:3000")
public class LecturerController {
}
