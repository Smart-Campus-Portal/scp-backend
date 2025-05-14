package tut.scp.shared.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.IssueRequest;
import tut.scp.dto.MaintenanceIssueUpdateRequest;
import tut.scp.entity.Issue;

import java.util.List;

public interface IMaintenance {
    ResponseEntity<?> reportIssue(IssueRequest request);
    ResponseEntity<?> getReportedIssues(Long userId);
    ResponseEntity<?> getAllReportedIssues();
    ResponseEntity<List<Issue>> getOpenIssues();
    ResponseEntity<?> updateIssueStatus(Long issueId, MaintenanceIssueUpdateRequest request);
}
