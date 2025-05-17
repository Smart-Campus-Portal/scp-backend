package tut.scp.lecturer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.Timetable;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    // Matches the field 'user' -> 'id'
    List<Timetable> findByUser_Id(Long userId);

    List<Timetable> findByTimetableID(Long timetableID);

    // Lookup by exact course name
    List<Timetable> findByCourse(String course);

    // Lookup by unique time field
    Optional<Timetable> findByTime(String time);
}
