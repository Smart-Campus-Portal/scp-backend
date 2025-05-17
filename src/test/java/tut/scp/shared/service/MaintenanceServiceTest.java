package tut.scp.shared.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tut.scp.dto.IssueRequest;
import tut.scp.dto.MaintenanceIssueUpdateRequest;
import tut.scp.entity.Issue;
import tut.scp.entity.User;
import tut.scp.enums.IssueCategory;
import tut.scp.enums.IssuePriority;
import tut.scp.enums.IssueStatus;
import tut.scp.repository.IssueRepository;
import tut.scp.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private IssueRepository issueRepo;

    @InjectMocks
    private MaintenanceService maintenanceService;

    private IssueRequest issueRequest;
    private User reporter;
    private Issue issue;
    private MaintenanceIssueUpdateRequest updateIssueRequest;

    @BeforeEach
    void setUp() {
        // new issue reporting data
        reporter = new User();
        reporter.setId(1L);
        reporter.setEmail("reporter@example.com");

        issueRequest = new IssueRequest();
        issueRequest.setReporterId(1L);
        issueRequest.setCategory(IssueCategory.SAFETY);
        issueRequest.setPriority(IssuePriority.LOW);
        issueRequest.setDescription("Light not working");
        issueRequest.setLocation("Room 205");

        // updating issue status
        issue = new Issue();
        issue.setId(1L);
        issue.setStatus(IssueStatus.REPORTED);

        updateIssueRequest = new MaintenanceIssueUpdateRequest();
        updateIssueRequest.setStatus(IssueStatus.IN_PROGRESS);
        updateIssueRequest.setAttendedBy(10L);
    }

    @Test
    void shouldReportIssueSuccessfully() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(reporter));

        ResponseEntity<?> response = maintenanceService.reportIssue(issueRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("Issue successfully reported", ((Map<?, ?>) response.getBody()).get("message"));
        verify(issueRepo).save(any(Issue.class));
    }

    @Test
    void shouldUpdateIssueStatusSuccessfully() {
        when(issueRepo.findById(1L)).thenReturn(Optional.of(issue));

        ResponseEntity<?> response = maintenanceService.updateIssueStatus(1L, updateIssueRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("Issue successfully updated", ((Map<?, ?>) response.getBody()).get("message"));
        assertEquals(IssueStatus.IN_PROGRESS, issue.getStatus());
        verify(issueRepo).save(issue);
    }

}

