package tut.scp.lecturer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.Timetable;
import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    
    List<Timetable> findByUser_Id(Long userId);
    List<Timetable> findByDay(String day);
    List<Timetable> findByCourse(String course);
}
