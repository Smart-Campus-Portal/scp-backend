package tut.scp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tut.scp.dto.StudyRoomBookingRequest;
import tut.scp.entity.StudyRoom;
import tut.scp.entity.StudyRoomBooking;
import tut.scp.entity.User;
import tut.scp.repository.StudyRoomBookingRepository;
import tut.scp.repository.StudyRoomRepository;
import tut.scp.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudyRoomServiceTest {
    @InjectMocks
    private StudyRoomService studyRoomService;

    @Mock
    private UserRepository userRepo;

    @Mock
    private StudyRoomRepository studyRoomRepo;

    @Mock
    private StudyRoomBookingRepository studyRoomBookingRepo;

    private StudyRoomBookingRequest request;
    private User student;
    private StudyRoom studyRoom;

    @BeforeEach
    void setUp() {
        student = new User();
        student.setId(1L);

        studyRoom = new StudyRoom();
        studyRoom.setId(10L);

        request = new StudyRoomBookingRequest();
        request.setStudentId(1L);
        request.setRoomId(10L);
        request.setStartTime("2025-05-17T10:00:00");
        request.setEndTime("2025-05-17T12:00:00");
    }

    /* Successful Booking */
    @Test
    void testBookStudyRoomSuccess() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(student));
        when(studyRoomRepo.findById(10L)).thenReturn(Optional.of(studyRoom));
        when(studyRoomBookingRepo.save(any(StudyRoomBooking.class))).thenAnswer(i -> i.getArgument(0));

        ResponseEntity<?> response = studyRoomService.bookStudyRoom(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertEquals("Room successfully booked.", body.get("message"));

        verify(userRepo).findById(1L);
        verify(studyRoomRepo).findById(10L);
        verify(studyRoomBookingRepo).save(any(StudyRoomBooking.class));
    }

}
