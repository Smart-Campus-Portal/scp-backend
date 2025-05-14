package tut.scp.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tut.scp.entity.Issue;
import tut.scp.enums.IssueStatus;
import tut.scp.repository.IssueRepository;
import tut.scp.repository.StudyRoomBookingRepository;
import tut.scp.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DashboardService implements IDashboard {

    private final UserRepository userRepo;
    private final StudyRoomBookingRepository studyRoomBookingRepo;
    private final IssueRepository issueRepo;

    @Autowired
    public DashboardService(UserRepository userRepo, StudyRoomBookingRepository studyRoomBookingRepo, IssueRepository issueRepo) {
        this.userRepo = userRepo;
        this.studyRoomBookingRepo = studyRoomBookingRepo;
        this.issueRepo = issueRepo;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getOverview() {
        Map<String, Object> data = new HashMap<>();

        // User counts by role
        List<Object[]> userCounts = userRepo.countUsersByRole();
        Map<String, Long> usersByRole = new HashMap<>();
        for (Object[] row : userCounts) {
            usersByRole.put(row[0].toString(), (Long) row[1]);
        }
        data.put("usersByRole", usersByRole);

        // Bookings by status
        List<Object[]> studyRoomBookingCounts = studyRoomBookingRepo.countStudyRoomBookingsByStatus();
        Map<String, Long> studyRoomBookingsByStatus = new HashMap<>();
        for (Object[] row : studyRoomBookingCounts) {
            studyRoomBookingsByStatus.put(row[0].toString(), (Long) row[1]);
        }
        data.put("studyRoomBookingsByStatus", studyRoomBookingsByStatus);

        // Maintenance issues by status
        List<Object[]> issueCounts = issueRepo.countIssuesByStatus();
        Map<String, Long> issuesByStatus = new HashMap<>();
        for (Object[] row : issueCounts) {
            issuesByStatus.put(row[0].toString(), (Long) row[1]);
        }
        data.put("maintenanceIssuesByStatus", issuesByStatus);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getTopBookedStudyRooms() {
        List<Object[]> results = studyRoomBookingRepo.findTopBookedStudyRooms();
        List<Map<String, Object>> response = results.stream()
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("roomName", row[0]);
                    map.put("bookingCount", row[1]);
                    return map;
                })
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
