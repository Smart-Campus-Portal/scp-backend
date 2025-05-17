package tut.scp.shared.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tut.scp.dto.IssueRequest;
import tut.scp.dto.MaintenanceIssueUpdateRequest;
import tut.scp.entity.Issue;
import tut.scp.entity.Notification;
import tut.scp.entity.User;
import tut.scp.enums.IssueStatus;
import tut.scp.repository.IssueRepository;
import tut.scp.repository.NotificationRepository;
import tut.scp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MaintenanceService implements IMaintenance {

    private final IssueRepository issueRepo;
    private final UserRepository userRepo;
    private final NotificationRepository notificationRepository;

    @Autowired
    public MaintenanceService(IssueRepository issueRepo, UserRepository userRepo, NotificationRepository notificationRepository) {
        this.issueRepo = issueRepo;
        this.userRepo = userRepo;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ResponseEntity<?> reportIssue(IssueRequest request) {
        Optional<User> user = userRepo.findById(request.getReporterId());
        log.info("Reporting issue by user {}", user.get().getId());
        Issue issue = new Issue();
        issue.setReporter(user.get());
        issue.setCategory(request.getCategory());
        issue.setPriority(request.getPriority());
        issue.setDescription(request.getDescription());
        issue.setLocation(request.getLocation());
        issueRepo.save(issue);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Issue successfully reported");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> getReportedIssues(Long reporterId) {
        log.info("Retrieving reported issues by reporter {}", reporterId);
        return ResponseEntity.status(HttpStatus.OK).body(issueRepo.findByReporterId(reporterId));
    }

    @Override
    public ResponseEntity<?> getAllReportedIssues() {
        log.info("Retrieving all reported issues");
        return ResponseEntity.status(HttpStatus.OK).body(issueRepo.findAll());
    }

    @Override
    public ResponseEntity<List<Issue>> getOpenIssues() {
        log.info("Retrieving open issues");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(issueRepo.findByStatus(IssueStatus.REPORTED));
    }

//    @Override
//    public ResponseEntity<?> updateIssueStatus(Long issueId, MaintenanceIssueUpdateRequest request) {
//        log.info("User {} updating issue status with id {}", request.getAttendedBy(), issueId);
//        Issue issue = issueRepo.findById(issueId).get();
//        issue.setStatus(request.getStatus());
//        issue.setUpdatedAt(LocalDateTime.now());
//        issueRepo.save(issue);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Issue successfully updated");
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @Override
    @Transactional
    public ResponseEntity<?> updateIssueStatus(Long issueId, MaintenanceIssueUpdateRequest request) {
        Optional<Issue> optionalIssue = issueRepo.findById(issueId);
        if (optionalIssue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue not found");
        }

        Issue issue = optionalIssue.get();
        issue.setStatus(request.getStatus());
        issueRepo.save(issue);

        // Prepare Notification
        User reporter = issue.getReporter();
        if (reporter != null) {
            String message = String.format("Your maintenance issue (ID: %d) status was updated to '%s' on %s",
                    issueId, request.getStatus(), LocalDateTime.now().toString());

            Notification notification = Notification.builder()
                    .message(message)
                    .timestamp(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusDays(7)) // Expire in 7 days
                    .read(false)
                    .user(reporter)
                    .build();

            notificationRepository.save(notification);
        }

        return ResponseEntity.ok("Issue status updated and user notified.");
    }

    @Override
    public ResponseEntity<Map<String, Object>> countReportedIssues(Long reporterId) {
        log.info("Counting issues reported by user {}", reporterId);
        long count = issueRepo.countByReporterId(reporterId);
        return ResponseEntity.ok(Map.of(
                "reporterId", reporterId,
                "count", count));
    }

    @Override
    public ResponseEntity<Map<String, Object>> countNotResolvedIssues() {
        log.info("Counting issues not resolved");
        long count = issueRepo.countByStatusNot(IssueStatus.RESOLVED);
        return ResponseEntity.ok(Map.of(
                "statusNot", IssueStatus.RESOLVED,
                "count", count));
    }

    @Override
    public ResponseEntity<Map<String, Long>> countIssuesByStatus() {
        log.info("Counting issues grouped by status");
        List<Object[]> raw = issueRepo.countIssuesByStatus();
        // Convert List<Object[]> to Map<IssueStatus, Long>
        Map<String, Long> map = raw.stream().collect(Collectors.toMap(
                row -> ((IssueStatus) row[0]).name(),
                row -> (Long) row[1]));
        return ResponseEntity.ok(map);
    }
}
