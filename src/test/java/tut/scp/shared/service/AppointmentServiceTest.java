package tut.scp.shared.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tut.scp.dto.AppointmentRequest;
import tut.scp.entity.Appointment;
import tut.scp.entity.User;
import tut.scp.repository.AppointmentRepository;
import tut.scp.repository.UserRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private AppointmentRepository appointmentRepo;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentRequest request;
    private User student;
    private User lecturer;

    @BeforeEach
    void setUp() {
        request = new AppointmentRequest();
        request.setStudentId(1L);
        request.setLecturerId(2L);
        request.setDate(Date.valueOf(LocalDate.of(2025, 5, 20)));
        request.setTime(Time.valueOf(LocalTime.of(14, 30)));
        request.setCourseName("Computer Science");
        request.setModuleName("Software Engineering");
        request.setLocation("Room 101");
        request.setDescription("Discuss project.");

        student = new User();
        student.setId(1L);
        student.setEmail("student@test.com");

        lecturer = new User();
        lecturer.setId(2L);
        lecturer.setEmail("lecturer@test.com");
    }

    @Test
    void shouldBookAppointmentSuccessfully() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(student));
        when(userRepo.findById(2L)).thenReturn(Optional.of(lecturer));

        ResponseEntity<?> response = appointmentService.bookAppointment(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("Appointment successfully booked.", ((Map<?, ?>) response.getBody()).get("message"));

        verify(appointmentRepo).save(any(Appointment.class));
    }

}

