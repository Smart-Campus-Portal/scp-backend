package tut.scp.lecturer.Interface;

import org.springframework.http.ResponseEntity;

import tut.scp.entity.LecturerSchedule;

public interface LecturerScheduleInterface {

    ResponseEntity<?> makeSchedule(LecturerSchedule schedule);
    ResponseEntity<?> getSchedule(Long lecturerID);

}
