package tut.scp.lecturer.Interface;

import org.springframework.http.ResponseEntity;

import tut.scp.entity.Timetable;

public interface TimetableInterface {

    ResponseEntity<?> getTimetable(String course);
    ResponseEntity<?> makeTimetable(Timetable timetable);

}
