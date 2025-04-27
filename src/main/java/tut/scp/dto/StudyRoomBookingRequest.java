package tut.scp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomBookingRequest {
    Long studentId;
    Long roomId;
    String startTime;
    String endTime;
}
