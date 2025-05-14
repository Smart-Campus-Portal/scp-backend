package tut.scp.lecturer.Manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tut.scp.entity.LecturerSchedule;
import tut.scp.lecturer.Interface.LecturerScheduleInterface;
import tut.scp.lecturer.Repository.LecturerScheduleRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LecturerScheduleManager implements LecturerScheduleInterface {

    private final LecturerScheduleRepository lecturerRepository;

    @Override
    public ResponseEntity<?> getSchedule(Long lecturerID) {
        try {
            Optional<LecturerSchedule> scheduleOpt = lecturerRepository.findByUser_Id(lecturerID);

            if (scheduleOpt.isPresent()) {
                return new ResponseEntity<>(scheduleOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Schedule not found for Lecturer ID: " + lecturerID, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> makeSchedule(LecturerSchedule schedule) {
        try {
            LecturerSchedule saved = lecturerRepository.save(schedule);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save schedule: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
