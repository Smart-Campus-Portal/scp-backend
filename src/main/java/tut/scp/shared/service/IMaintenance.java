package tut.scp.shared.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.IssueRequest;

public interface IMaintenance {
    ResponseEntity<?> reportIssue(IssueRequest request);
    ResponseEntity<?> getReportedIssues(Long userId);
}
