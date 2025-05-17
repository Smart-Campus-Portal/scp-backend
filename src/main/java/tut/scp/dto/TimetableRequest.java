package tut.scp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TimetableRequest {

    private String name;
    private String course;
    private String location;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String time;
    private String dayData;
    private Long userId; // ✅ Use camelCase to match Java conventions
}
