package tut.scp.lecturer.Interface;

import org.springframework.http.ResponseEntity;
import tut.scp.entity.Appointment;

import java.util.List;

public interface AppointmentInterface {

    ResponseEntity<List<Appointment>> getAppointments(Long lecturerID);

    ResponseEntity<Appointment> makeAppointment(Appointment appointment);
}
