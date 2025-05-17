package tut.scp.shared.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.AppointmentRequest;

public interface IAppointment {
    ResponseEntity<?> bookAppointment(AppointmentRequest request);
    ResponseEntity<?> getLecturers();
    ResponseEntity<?> viewAppointments(Long userId);
}
