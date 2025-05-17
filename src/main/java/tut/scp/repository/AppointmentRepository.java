package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.Appointment;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByLecturerId(Long lecturerId);
    List<Appointment> findAllByStudentId(Long studentId);
}


