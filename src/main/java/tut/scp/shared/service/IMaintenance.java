package tut.scp.shared.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.IssueRequest;
import tut.scp.dto.MaintenanceIssueUpdateRequest;
import tut.scp.entity.Issue;

import java.util.List;
import java.util.Map;

public interface IMaintenance {
    ResponseEntity<?> reportIssue(IssueRequest request);
    ResponseEntity<?> getReportedIssues(Long userId);
    ResponseEntity<?> getAllReportedIssues();
    ResponseEntity<List<Issue>> getOpenIssues();
    ResponseEntity<?> updateIssueStatus(Long issueId, MaintenanceIssueUpdateRequest request);
    /**
     * Returns the count of issues reported by the given user.
     * Response: { "reporterId": 1, "count": 5 }
     */
    ResponseEntity<Map<String, Object>> countReportedIssues(Long reporterId);

    /**
     * Returns the count of all issues whose status is not RESOLVED.
     * Response: { "statusNot": "RESOLVED", "count": 17 }
     */
    ResponseEntity<Map<String, Object>> countNotResolvedIssues();

    /**s
     * Returns a breakdown of issue counts grouped by status.
     * Response: { "REPORTED": 10, "ATTENDED": 3, "RESOLVED": 7 }
     */
    ResponseEntity<Map<String, Long>> countIssuesByStatus();
}
