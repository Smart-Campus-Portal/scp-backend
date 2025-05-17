package tut.scp.shared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.AppointmentRequest;
import tut.scp.shared.service.IAppointment;

@RestController
@RequestMapping("/api/appointment")
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    private final IAppointment appointmentService;
    @Autowired
    public AppointmentController(IAppointment appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_LECTURER')")
    @PostMapping("/view-bookings")
    public ResponseEntity<?> viewAppointmentBookings(@RequestParam Long userId) {
        return appointmentService.viewAppointments(userId);
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/book")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentRequest request) {
        return appointmentService.bookAppointment(request);
    }


    @GetMapping("/get-all-lecturers")
    public ResponseEntity<?> getAllLecturers() {
        return appointmentService.getLecturers();
    }
}
