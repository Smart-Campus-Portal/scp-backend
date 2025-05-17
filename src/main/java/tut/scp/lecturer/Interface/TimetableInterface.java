package tut.scp.lecturer.Interface;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import tut.scp.dto.TimetableRequest;
import tut.scp.entity.Timetable;

public interface TimetableInterface {

    ResponseEntity<?> makeTimetable(TimetableRequest timetableData);

    ResponseEntity<?> updateTimetableDayData(String time, String day, String dayData, String location);

    ResponseEntity<?> getTimetableDayData(String time, String day);

    ResponseEntity<?> updateTimetableTime(Long id, String time);

    ResponseEntity<?> deleteTimetableDayData(String time, String day);

    ResponseEntity<?> deleteTimetableLine(String time, Long id);

    ResponseEntity<List<Timetable>> getTimetable(Long userID);

    ResponseEntity<List<Timetable>> getTimetableByCourse(String course);

    ResponseEntity<Map<String,String>> getTimetableByToday(String today);


}
