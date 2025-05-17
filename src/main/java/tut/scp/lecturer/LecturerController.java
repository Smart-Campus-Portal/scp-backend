package tut.scp.lecturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.TimetableRequest;
import tut.scp.entity.LecturerSchedule;
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

    @Autowired
    public LecturerController(
            LecturerScheduleInterface lecturerScheduleInterface,
            TimetableInterface timetableInterface,
            AppointmentInterface appointmentInterface) {

        this.lecturerScheduleInterface = lecturerScheduleInterface;
        this.timetableInterface = timetableInterface;
        this.appointmentInterface = appointmentInterface;
    }

    // Endpoint to retrieve a lecturer's schedule by lecturer ID
    // Get lecturer's schedule
    @GetMapping("/schedule/{lecturerId}")
    public ResponseEntity<?> getLecturerSchedule(@PathVariable Long lecturerId) {
        return lecturerScheduleInterface.getSchedule(lecturerId);
    }

    // Endpoint to create or update a lecturer's schedule
    @PostMapping("/schedule")
    public ResponseEntity<?> createSchedule(@RequestBody LecturerSchedule schedule) {
        return lecturerScheduleInterface.makeSchedule(schedule);
    }

    // Timetable Management APIs:

    // Create or update timetable
    @PostMapping("/makeTimetable")
    public ResponseEntity<?> createTimetable(@RequestBody TimetableRequest timetable) {
        return timetableInterface.makeTimetable(timetable);
    }

    // Create dayData for timetable
    @PostMapping("/timetable/updateDayData")
    public ResponseEntity<?> createDayData(
            @RequestParam("time") String time,
            @RequestParam("day") String day,
            @RequestParam("dayData") String dayData,
            @RequestParam("location") String location) {
        return timetableInterface.updateTimetableDayData(time, day, dayData, location);
    }

    // View timetable by lecturer ID
    @GetMapping("/getTimetableByID")
    public ResponseEntity<?> viewTimetable(@RequestParam("userID") Long userID) {
        return timetableInterface.getTimetable(userID);

    }

    // Endpoint to get appointments for a lecturer
    // View timetable by lecturer ID
    @GetMapping("/getTimetableByCourse")
    public ResponseEntity<?> viewTimetable(@RequestParam("course") String course) {
        return timetableInterface.getTimetableByCourse(course);
    }

    // Get dayData for timetable
    @GetMapping("/timetable/getDayData")
    public ResponseEntity<?> getDayData(
            @RequestParam("time") String time,
            @RequestParam("day") String day) {
        return timetableInterface.getTimetableDayData(time, day);
    }

  
    // Update timetable time by ID
    @PutMapping("/timetable/updateTime")
    public ResponseEntity<?> updateTime(
            @RequestParam("id") Long id,
            @RequestParam("time") String time) {
        return timetableInterface.updateTimetableTime(id, time);
    }

    // Delete dayData from timetable
    @DeleteMapping("/timetable/deletDayData")
    public ResponseEntity<?> deleteDayData(
            @RequestParam("time") String time,
            @RequestParam("day") String day) {
        return timetableInterface.deleteTimetableDayData(time, day);
    }

    // Delete dayData from timetable
    @DeleteMapping("/timetable/deleteTimetableRowLine")
    public ResponseEntity<?> deleteTimetableRowLine(
            @RequestParam("time") String time,
            @RequestParam("id") Long id) {
        return timetableInterface.deleteTimetableLine(time, id);
    }

    // Get lecturer's appointments
    @GetMapping("/getAppointment")
    public ResponseEntity<?> getAppointment(@RequestParam("lecturerID") Long lecturerID) {
        return appointmentInterface.getAppointments(lecturerID);
    }

}
