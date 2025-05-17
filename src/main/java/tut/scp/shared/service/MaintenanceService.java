package tut.scp.shared.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tut.scp.dto.IssueRequest;
import tut.scp.dto.MaintenanceIssueUpdateRequest;
import tut.scp.entity.Issue;
import tut.scp.entity.User;
import tut.scp.enums.IssueStatus;
import tut.scp.repository.IssueRepository;
import tut.scp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class MaintenanceService implements IMaintenance {

    private final IssueRepository issueRepo;
    private final UserRepository userRepo;

    @Autowired
    public MaintenanceService(IssueRepository issueRepo, UserRepository userRepo) {
        this.issueRepo = issueRepo;
        this.userRepo = userRepo;
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

    @Override
    public ResponseEntity<?> updateIssueStatus(Long issueId, MaintenanceIssueUpdateRequest request) {
        log.info("User {} updating issue status with id {}", request.getAttendedBy(), issueId);
        Issue issue = issueRepo.findById(issueId).get();
        issue.setStatus(request.getStatus());
        issue.setUpdatedAt(LocalDateTime.now());
        issueRepo.save(issue);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Issue successfully updated");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
