package tut.scp.lecturer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.LecturerSchedule;


public interface LecturerScheduleRepository   extends JpaRepository<LecturerSchedule, Long> {

    Optional<LecturerSchedule> findByCourse(String course);
    Optional<LecturerSchedule> findByUser_Id(Long userId);
    
}
