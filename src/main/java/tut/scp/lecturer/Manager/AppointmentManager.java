package tut.scp.lecturer.Manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tut.scp.entity.Appointment;
import tut.scp.lecturer.Interface.AppointmentInterface;
import tut.scp.repository.AppointmentRepository;

import java.util.List;
import java.util.Collections;

@Service
@AllArgsConstructor
public class AppointmentManager implements AppointmentInterface {

    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<List<Appointment>> getAppointments(Long lecturerID) {
        try {
            List<Appointment> appointments = appointmentRepository.findAllByLecturerId(lecturerID);
            if (appointments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.emptyList());
            }
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @Override
    public ResponseEntity<Appointment> makeAppointment(Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }
}
