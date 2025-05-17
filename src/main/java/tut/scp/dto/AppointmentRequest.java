package tut.scp.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class AppointmentRequest {
    private Long studentId;
    private Long lecturerId;
    private Date date;
    private Time time;
    private String location;
    private String description;
    private String courseName;
    private String moduleName;

}
