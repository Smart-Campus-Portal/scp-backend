package tut.scp.student;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.AppointmentRequest;
import tut.scp.dto.UserResponse;

import java.util.List;

public interface IAppointment {
    ResponseEntity<?> bookAppointment(AppointmentRequest request);
    ResponseEntity<?> getLecturers();
}
