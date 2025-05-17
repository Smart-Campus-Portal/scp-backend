package tut.scp.lecturer.Manager;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tut.scp.entity.Timetable;
import tut.scp.dto.TimetableRequest;
import tut.scp.lecturer.Interface.TimetableInterface;
import tut.scp.lecturer.Repository.TimetableRepository;
import tut.scp.repository.UserRepository;
import tut.scp.entity.User;
import java.util.*;

@Service
@AllArgsConstructor
public class TimetableManager implements TimetableInterface {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<?> makeTimetable(TimetableRequest dto) {
        // 1. Validate user ID
        if (dto.getUserId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "User ID must be provided."));
        }

        // 2. Fetch User
        User user = userRepository.findById(dto.getUserId()).get();

        // Debug print to confirm the user is fetched correctly
        System.out.println("Fetched user ID: " + user.getId()); // Make sure this prints a valid ID
        // 3. Create and map Timetable
        Timetable timetable = new Timetable();
        timetable.setName(dto.getName());
        timetable.setCourse(dto.getCourse());
        timetable.setLocation(dto.getLocation());
        timetable.setMonday(dto.getMonday());
        timetable.setTuesday(dto.getTuesday());
        timetable.setWednesday(dto.getWednesday());
        timetable.setThursday(dto.getThursday());
        timetable.setFriday(dto.getFriday());
        timetable.setTime(dto.getTime());
        timetable.setDayData(dto.getDayData() != null ? dto.getDayData() : "no class");

        // VERY IMPORTANT: Set user BEFORE saving
        timetable.setUser(user);

        // Debug print to confirm user is set
        System.out.println("Timetable's user ID: " + timetable.getUser().getId());
        // 4. Save to DB
        Timetable savedTimetable = timetableRepository.save(timetable);

        // 6. Return response
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Timetable created successfully.",
                        "data", savedTimetable));
    }

    @Override
    public ResponseEntity<?> getTimetableDayData(String time, String day) {
        Optional<Timetable> optional = timetableRepository.findByTime(time);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timetable at that time.");
        }

        Timetable timetable = optional.get();
        String value = getDayValue(timetable, day);
        return ResponseEntity.ok(Map.of("data", value != null ? value : "No data for " + day));
    }

    @Override
    public ResponseEntity<?> updateTimetableDayData(String time, String day, String dayData, String location) {
        Optional<Timetable> optional = timetableRepository.findByTime(time);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timetable row line found  at " + time);
        }

        Timetable timetableData = optional.get();
        setDayValue(timetableData, day, dayData);
        timetableData.setLocation(location);
        timetableRepository.save(timetableData);

        return ResponseEntity.ok(Map.of("message", "Day data updated successfully."));
    }

    @Override
    public ResponseEntity<?> updateTimetableTime(Long id, String time) {
        Optional<Timetable> optional = timetableRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Timetable not found.");
        }

        Timetable timetable = optional.get();
        timetable.setTime(time);
        timetableRepository.save(timetable);

        return ResponseEntity.ok(Map.of("message", "Time updated successfully."));
    }

    @Override
    public ResponseEntity<?> deleteTimetableDayData(String time, String day) {
        Optional<Timetable> optional = timetableRepository.findByTime(time);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No timetable at that time.");
        }

        Timetable timetable = optional.get();
        setDayValue(timetable, day, null);
        timetable.setLocation(null);
        timetableRepository.save(timetable);

        return ResponseEntity.ok(Map.of("message", "Day data deleted successfully."));
    }

    @Override
    public ResponseEntity<List<Timetable>> getTimetable(Long userID) {
        List<Timetable> timetables = timetableRepository.findByUser_Id(userID);
        return ResponseEntity.ok(timetables);
    }

    @Override
    public ResponseEntity<List<Timetable>> getTimetableByCourse(String course) {
        List<Timetable> timetables = timetableRepository.findByCourse(course);
        return ResponseEntity.ok(timetables);
    }

    private String getDayValue(Timetable timetable, String day) {
        return switch (day.toLowerCase()) {
            case "monday" -> timetable.getMonday();
            case "tuesday" -> timetable.getTuesday();
            case "wednesday" -> timetable.getWednesday();
            case "thursday" -> timetable.getThursday();
            case "friday" -> timetable.getFriday();
            default -> null;
        };
    }

    private void setDayValue(Timetable timetable, String day, String value) {
        switch (day.toLowerCase()) {
            case "monday" -> timetable.setMonday(value);
            case "tuesday" -> timetable.setTuesday(value);
            case "wednesday" -> timetable.setWednesday(value);
            case "thursday" -> timetable.setThursday(value);
            case "friday" -> timetable.setFriday(value);
        }
    }

    @Override
    public ResponseEntity<?> deleteTimetableLine(String time, Long id) {
        Map<String, Object> response = new HashMap<>();

        if (time == null || time.isEmpty() || id == null) {
            response.put("error", " time be  must be provided.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Fetch timetable entries matching user and time
        List<Timetable> entries = timetableRepository.findByTimetableID(id);
        if (entries.isEmpty()) {
            response.put("error", "No timetable entry found for the given user and time.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Delete the found entries
        timetableRepository.deleteAll(entries);
        response.put("message", "Timetable entry(ies) deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, String>> getTimetableByToday(String today) {
        Map<String, String> response = new HashMap<>();

        List<Timetable> allTimetables = timetableRepository.findAll();

        for (Timetable timetable : allTimetables) {
            boolean hasClassToday = false;

            switch (today.toLowerCase()) {
                case "monday":
                    hasClassToday = timetable.getMonday() != null
                            && !timetable.getMonday().equalsIgnoreCase("no class");
                    break;
                case "tuesday":
                    hasClassToday = timetable.getTuesday() != null
                            && !timetable.getTuesday().equalsIgnoreCase("no class");
                    break;
                case "wednesday":
                    hasClassToday = timetable.getWednesday() != null
                            && !timetable.getWednesday().equalsIgnoreCase("no class");
                    break;
                case "thursday":
                    hasClassToday = timetable.getThursday() != null
                            && !timetable.getThursday().equalsIgnoreCase("no class");
                    break;
                case "friday":
                    hasClassToday = timetable.getFriday() != null
                            && !timetable.getFriday().equalsIgnoreCase("no class");
                    break;
                default:
                    return ResponseEntity.badRequest().body(Map.of("error", "Invalid day provided."));
            }

            if (hasClassToday) {
                response.put(timetable.getTime(), timetable.getDayData());
            }
        }

        if (response.isEmpty()) {
            response.put("message", "No timetable entries found for " + today);
        }

        return ResponseEntity.ok(response);
    }

}
