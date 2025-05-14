package tut.scp.lecturer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.entity.LecturerSchedule;
import tut.scp.entity.Timetable;
import tut.scp.lecturer.Interface.AppointmentInterface;
import tut.scp.lecturer.Interface.LecturerScheduleInterface;
import tut.scp.lecturer.Interface.TimetableInterface;

@RestController
@RequestMapping("/api/lecturer")
@PreAuthorize("hasRole('ROLE_LECTURER')")
@CrossOrigin(origins = "http://localhost:3000")
public class LecturerController {

    private final LecturerScheduleInterface lecturerScheduleInterface;
    private final TimetableInterface timetableInterface;
    private final AppointmentInterface appointmentInterface;

    public LecturerController(LecturerScheduleInterface lecturerScheduleInterface, TimetableInterface timetableInterface, AppointmentInterface appointmentInterface) {
        this.lecturerScheduleInterface = lecturerScheduleInterface;
        this.timetableInterface = timetableInterface;
        this.appointmentInterface = appointmentInterface;
    }

    // Endpoint to retrieve a lecturer's schedule by lecturer ID
    @GetMapping("/schedule/{lecturerId}")
    public ResponseEntity<?> getLecturerSchedule(@PathVariable Long lecturerId) {
        return lecturerScheduleInterface.getSchedule(lecturerId);
    }

    // Endpoint to create or update a lecturer's schedule
    @PostMapping("/schedule")
    public ResponseEntity<?> createSchedule(@RequestBody LecturerSchedule schedule) {
        return lecturerScheduleInterface.makeSchedule(schedule);
    }

    // Endpoint to create or update the timetable
    @PostMapping("/timetable")
    public ResponseEntity<?> createTimetable(@RequestBody Timetable timetable) {
        return timetableInterface.makeTimetable(timetable);
    }

    // Endpoint to view the timetable
    @GetMapping("/getTimetable")
    public ResponseEntity<?>  viewTimetable(@RequestParam("course") String course){

        return timetableInterface.getTimetable(course);

    }

    // Endpoint to get appointments for a lecturer
    @GetMapping("/getAppointment")
    public ResponseEntity<?> getAppointment(@RequestParam("lecturerID") Long lecturerID) {
        return appointmentInterface.getAppointments(lecturerID);
    }

}
