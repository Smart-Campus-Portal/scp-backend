package tut.scp.lecturer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tut.scp.entity.LecturerSchedule;
import tut.scp.entity.Timetable;
import tut.scp.lecturer.Interface.LecturerScheduleInterface;
import tut.scp.lecturer.Interface.TimetableInterface;

@RestController
@RequestMapping("/api/lecturer")
public class LecturerController {

    private final LecturerScheduleInterface lecturerScheduleInterface;
    private final TimetableInterface timetableInterface;

    @Autowired
    public LecturerController(LecturerScheduleInterface lecturerScheduleInterface, TimetableInterface timetableInterface) {
        this.lecturerScheduleInterface = lecturerScheduleInterface;
        this.timetableInterface = timetableInterface;
    }

    // Endpoint to retrieve lecturer's schedule by lecturer ID
    @PreAuthorize("hasRole('ROLE_LECTURER')")
    @GetMapping("/schedule/{lecturerId}")
    public ResponseEntity<?> getLecturerSchedule(@PathVariable Long lecturerId) {
        return lecturerScheduleInterface.getSchedule(lecturerId);
    }

    // Endpoint to create or update a lecturer's schedule
    @PreAuthorize("hasRole('ROLE_LECTURER')")
    @PostMapping("/schedule")
    public ResponseEntity<?> createSchedule(@RequestBody LecturerSchedule schedule) {
        return lecturerScheduleInterface.makeSchedule(schedule);
    }

    // Endpoint to create or update the timetable
    @PreAuthorize("hasRole('ROLE_LECTURER')")
    @PostMapping("/timetable")
    public ResponseEntity<?> createTimetable(@RequestBody Timetable timetable) {
        return timetableInterface.makeTimetable(timetable);
    }

 // Endpoint to view the timetable
 @PreAuthorize("hasRole('ROLE_LECTURER')")
 @GetMapping("/getTimetable")
public ResponseEntity<?>  viewTimetable(@RequestParam("course") String course){

    return timetableInterface.getTimetable(course);

}

}
