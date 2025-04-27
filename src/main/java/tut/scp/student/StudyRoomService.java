package tut.scp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tut.scp.dto.StudyRoomBookingRequest;
import tut.scp.entity.StudyRoom;
import tut.scp.entity.StudyRoomBooking;
import tut.scp.entity.User;
import tut.scp.repository.StudyRoomBookingRepository;
import tut.scp.repository.StudyRoomRepository;
import tut.scp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudyRoomService implements IStudyRoom {

    private final StudyRoomRepository studyRoomRepo;
    private final StudyRoomBookingRepository studyRoomBookingRepo;
    private final UserRepository userRepo;

    @Autowired
    public StudyRoomService(StudyRoomRepository studyRoomRepository, StudyRoomBookingRepository studyRoomBookingRepository, UserRepository userRepository) {
        this.studyRoomRepo = studyRoomRepository;
        this.studyRoomBookingRepo = studyRoomBookingRepository;
        this.userRepo = userRepository;
    }

    @Override
    public ResponseEntity<?> getAvailableStudyRooms(LocalDateTime startTime, LocalDateTime endTime) {
        List<Long> bookedRoomIds = studyRoomBookingRepo.findBookedRoomIds(startTime, endTime);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Available Study Rooms");
        if (bookedRoomIds.isEmpty()) {
            response.put("data", studyRoomRepo.findAll());
        } else {
            response.put("data", studyRoomRepo.findByIdNotIn(bookedRoomIds));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Override
    public ResponseEntity<?> bookStudyRoom(StudyRoomBookingRequest request) {

        Optional<User> student = userRepo.findById(request.getStudentId());
        Optional<StudyRoom> studyRoom = studyRoomRepo.findById(request.getRoomId());

        LocalDateTime startTime = LocalDateTime.parse(request.getStartTime());
        LocalDateTime endTime = LocalDateTime.parse(request.getEndTime());

        StudyRoomBooking studyRoomBooking = new StudyRoomBooking();
        studyRoomBooking.setStudent(student.get());
        studyRoomBooking.setRoom(studyRoom.get());
        studyRoomBooking.setStartTime(startTime);
        studyRoomBooking.setEndTime(endTime);
        studyRoomBookingRepo.save(studyRoomBooking);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Room successfully booked.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
