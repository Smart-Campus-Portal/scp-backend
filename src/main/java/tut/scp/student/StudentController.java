package tut.scp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.StudyRoomBookingRequest;
import tut.scp.lecturer.Interface.AppointmentInterface;
import tut.scp.lecturer.Interface.TimetableInterface;
import tut.scp.shared.service.IAppointment;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('ROLE_STUDENT')")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final IStudyRoom studyRoomService;
    private final TimetableInterface timetable;
    private final AppointmentInterface appointmentInterface;
    private final IAppointment appointmentService;

    @Autowired
    public StudentController(
            IStudyRoom studyRoomBookingService,
            TimetableInterface timetable,
            AppointmentInterface appointmentInterface,
            IAppointment appointmentService) {

        this.studyRoomService = studyRoomBookingService;
        this.timetable = timetable;
        this.appointmentInterface = appointmentInterface;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/view-timetable")
    public ResponseEntity<?> viewTimetable(@RequestParam("course") String course) {
        return timetable.getTimetableByCourse(course);

    }

    // Get today's timetable data (dayData) for lecturer
    @GetMapping("/getTimetableByToday")
    public ResponseEntity<?> getTimetableByToday(@RequestParam("today") String today) {
        return timetable.getTimetableByToday(today);
    }

    @PostMapping("/available-study-rooms")
    public ResponseEntity<?> getAvailableStudyRooms(@RequestParam("startTime") String startTimeStr,
            @RequestParam("endTime") String endTimeStr) {
        return studyRoomService.getAvailableStudyRooms(LocalDateTime.parse(startTimeStr),
                LocalDateTime.parse(endTimeStr));
    }

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
