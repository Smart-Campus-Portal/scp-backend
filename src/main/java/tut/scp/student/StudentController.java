package tut.scp.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.StudyRoomBookingRequest;
import tut.scp.lecturer.Interface.AppointmentInterface;
import tut.scp.lecturer.Interface.TimetableInterface;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final IStudyRoom studyRoomService;
    private final TimetableInterface timetable;
    private final AppointmentInterface appointmentInterface;

    public StudentController(IStudyRoom studyRoomBookingService, TimetableInterface timetable,
            AppointmentInterface appointmentInterface) {
        this.studyRoomService = studyRoomBookingService;
        this.timetable = timetable;
        this.appointmentInterface = appointmentInterface;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/view-timetable")
    public ResponseEntity<?> viewTimetable(@RequestParam("course") String course) {
        return timetable.getTimetableByCourse(course);

    }

    // Get today's timetable data (dayData) for lecturer
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/getTimetableByToday")
    public ResponseEntity<?> getTimetableByToday(@RequestParam("today") String today) {
        return timetable.getTimetableByToday(today);
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/available-study-rooms")
    public ResponseEntity<?> getAvailableStudyRooms(@RequestParam("startTime") String startTimeStr,
            @RequestParam("endTime") String endTimeStr) {
        return studyRoomService.getAvailableStudyRooms(LocalDateTime.parse(startTimeStr),
                LocalDateTime.parse(endTimeStr));
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/book-study-room")
    public ResponseEntity<?> bookStudyRoom(@RequestBody StudyRoomBookingRequest request) {
        return studyRoomService.bookStudyRoom(request);
    }

    // Endpoint to create an appointment
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/makeAppointment")
    public ResponseEntity<?> makeAppointment(@RequestBody tut.scp.entity.Appointment appointment) {
        return appointmentInterface.makeAppointment(appointment);
    }
}
