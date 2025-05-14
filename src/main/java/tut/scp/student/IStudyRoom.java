package tut.scp.student;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.StudyRoomBookingRequest;

import java.time.LocalDateTime;

public interface IStudyRoom {
    ResponseEntity<?> getAvailableStudyRooms(LocalDateTime startTime, LocalDateTime endTime);
    ResponseEntity<?> bookStudyRoom(StudyRoomBookingRequest request);
<<<<<<< HEAD
=======
    
>>>>>>> lecturer-local/lecturer
}
