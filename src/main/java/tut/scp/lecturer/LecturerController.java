package tut.scp.lecturer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lecturer")
@CrossOrigin(origins = "http://localhost:3000")
public class LecturerController {
}
