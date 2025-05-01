package tut.scp.shared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.IssueRequest;
import tut.scp.shared.service.IMaintenance;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {

    private final IMaintenance maintenanceService;

    @Autowired
    public MaintenanceController(IMaintenance maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/report-issue")
    public ResponseEntity<?> reportMaintenanceIssue(@RequestBody IssueRequest issueRequest) {
        return maintenanceService.reportIssue(issueRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/issues")
    public ResponseEntity<?> getReportedMaintenanceIssues(@RequestParam Long reporterId) {
        return maintenanceService.getReportedIssues(reporterId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/issues/all")
    public ResponseEntity<?> getAllReportedMaintenanceIssues() {
        return maintenanceService.getAllReportedIssues();
    }

}
