package tut.scp.admin;

import org.springframework.http.ResponseEntity;
import tut.scp.entity.Issue;

import java.util.List;
import java.util.Map;

public interface IDashboard {
    ResponseEntity<Map<String, Object>> getOverview();
    ResponseEntity<List<Map<String, Object>>> getTopBookedStudyRooms();

}
