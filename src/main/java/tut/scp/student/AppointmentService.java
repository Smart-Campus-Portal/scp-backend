package tut.scp.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tut.scp.dto.AppointmentRequest;
import tut.scp.dto.UserResponse;
import tut.scp.entity.Appointment;
import tut.scp.entity.User;
import tut.scp.enums.Role;
import tut.scp.repository.AppointmentRepository;
import tut.scp.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AppointmentService implements IAppointment {

    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepo = appointmentRepository;
        this.userRepo = userRepository;
    }

    @Override
    public ResponseEntity<?> bookAppointment(AppointmentRequest request) {
        User student = userRepo.findById(request.getStudentId()).orElse(null);
        User lecturer = userRepo.findById(request.getLecturerId()).orElse(null);
        log.info("Student {} booked appointment with lecturer {} on date {} at time {}.",
                student.getEmail(), lecturer.getEmail(), request.getDate(), request.getTime());
        Appointment newAppointment = new Appointment();
        newAppointment.setStudent(student);
        newAppointment.setLecturer(lecturer);
        newAppointment.setDate(request.getDate());
        newAppointment.setTime(request.getTime());
        newAppointment.setCourseName(request.getCourseName());
        newAppointment.setModuleName(request.getModuleName());
        newAppointment.setLocation(request.getLocation());
        newAppointment.setDescription(request.getDescription());
        appointmentRepo.save(newAppointment);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Appointment successfully booked.");
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @Override
    public ResponseEntity<?> getLecturers() {
        log.info("Getting all lecturers...");
        List<User> lecturers = userRepo.findByRole(Role.ROLE_LECTURER);
        if (lecturers.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "No lecturers found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        List<UserResponse> foundLecturers = lecturers.stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().toString()))
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(foundLecturers);
    }
}
