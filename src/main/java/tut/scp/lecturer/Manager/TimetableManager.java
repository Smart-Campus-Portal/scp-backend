package tut.scp.lecturer.Manager;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tut.scp.entity.Timetable;
import tut.scp.lecturer.Interface.TimetableInterface;
import tut.scp.lecturer.Repository.TimetableRepository;

@Service
@AllArgsConstructor
public class TimetableManager implements TimetableInterface {

    private final TimetableRepository timetableRepository;

    @Override
    public ResponseEntity<?> getTimetable(String course) {
        try {
            List<Timetable> timetables = timetableRepository.findByCourse(course);
            if (timetables.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No timetable found for course: " + course);
            }
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving timetable: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> makeTimetable(Timetable timetable) {
        Timetable savedTimetable = timetableRepository.save(timetable);
        return new ResponseEntity<>(savedTimetable, HttpStatus.CREATED);
    }
}
