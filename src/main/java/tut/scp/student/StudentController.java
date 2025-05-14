package tut.scp.student;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======

>>>>>>> lecturer-local/lecturer
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.StudyRoomBookingRequest;
<<<<<<< HEAD
=======
import tut.scp.lecturer.Interface.AppointmentInterface;
import tut.scp.lecturer.Interface.TimetableInterface;
>>>>>>> lecturer-local/lecturer

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/student")
<<<<<<< HEAD
@PreAuthorize("hasRole('ROLE_STUDENT')")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final IStudyRoom studyRoomService;

    @Autowired
    public StudentController(IStudyRoom studyRoomBookingService) {
        this.studyRoomService = studyRoomBookingService;
    }

    @GetMapping("/view-timetable")
    public ResponseEntity<?> viewTimetable() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Timetable");
    }

=======
public class StudentController {

    private final IStudyRoom studyRoomService;
    private final TimetableInterface timetable;
    private final AppointmentInterface appointmentInterface;

    
    public StudentController(IStudyRoom studyRoomBookingService, TimetableInterface timetable, AppointmentInterface appointmentInterface) {
        this.studyRoomService = studyRoomBookingService;
        this.timetable = timetable;
        this.appointmentInterface = appointmentInterface;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/view-timetable")
    public ResponseEntity<?> viewTimetable(@RequestParam("course") String course) {
        return timetable.getTimetable(course);
    }
    
    @PreAuthorize("hasRole('ROLE_STUDENT')")
>>>>>>> lecturer-local/lecturer
    @PostMapping("/available-study-rooms")
    public ResponseEntity<?> getAvailableStudyRooms(@RequestParam("startTime") String startTimeStr, @RequestParam("endTime") String endTimeStr) {
        return studyRoomService.getAvailableStudyRooms(LocalDateTime.parse(startTimeStr), LocalDateTime.parse(endTimeStr));
    }

<<<<<<< HEAD
=======
    @PreAuthorize("hasRole('ROLE_STUDENT')")
>>>>>>> lecturer-local/lecturer
    @PostMapping("/book-study-room")
    public ResponseEntity<?> bookStudyRoom(@RequestBody StudyRoomBookingRequest request) {
        return studyRoomService.bookStudyRoom(request);
    }
<<<<<<< HEAD

=======
 // Endpoint to create an appointment
 @PreAuthorize("hasRole('ROLE_STUDENT')")
 @PostMapping("/makeAppointment")
 public ResponseEntity<?> makeAppointment(@RequestBody tut.scp.entity.Appointment appointment) {
     return appointmentInterface.makeAppointment(appointment);
 }
>>>>>>> lecturer-local/lecturer
}
