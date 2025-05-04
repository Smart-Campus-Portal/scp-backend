package tut.scp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.StudyRoomBookingRequest;
import tut.scp.lecturer.Interface.TimetableInterface;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final IStudyRoom studyRoomService;
    private final TimetableInterface timetable;

    @Autowired
    public StudentController(IStudyRoom studyRoomBookingService, TimetableInterface timetable) {
        this.studyRoomService = studyRoomBookingService;
        this.timetable = timetable;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/view-timetable")
    public ResponseEntity<?> viewTimetable(@RequestParam("course") String course) {
        return timetable.getTimetable(course);
    }
    
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/available-study-rooms")
    public ResponseEntity<?> getAvailableStudyRooms(@RequestParam("startTime") String startTimeStr, @RequestParam("endTime") String endTimeStr) {
        return studyRoomService.getAvailableStudyRooms(LocalDateTime.parse(startTimeStr), LocalDateTime.parse(endTimeStr));
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping("/book-study-room")
    public ResponseEntity<?> bookStudyRoom(@RequestBody StudyRoomBookingRequest request) {
        return studyRoomService.bookStudyRoom(request);
    }

}
