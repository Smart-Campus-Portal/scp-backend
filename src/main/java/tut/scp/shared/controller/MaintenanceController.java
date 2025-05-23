package tut.scp.shared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tut.scp.dto.IssueRequest;
import tut.scp.dto.MaintenanceIssueUpdateRequest;
import tut.scp.shared.service.IMaintenance;
import tut.scp.shared.service.NotificationService;

@RestController
@RequestMapping("/api/maintenance")
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {

    private final IMaintenance maintenanceService;
    private final NotificationService notificationService;

    @Autowired
    public MaintenanceController(NotificationService notificationService, IMaintenance maintenanceService) {
        this.notificationService = notificationService;
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/report-issue")
    public ResponseEntity<?> reportMaintenanceIssue(@RequestBody IssueRequest issueRequest) {
        return maintenanceService.reportIssue(issueRequest);
    }

    @GetMapping("/issues")
    public ResponseEntity<?> getReportedMaintenanceIssues(@RequestParam Long reporterId) {
        return maintenanceService.getReportedIssues(reporterId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/issues/all")
    public ResponseEntity<?> getAllReportedMaintenanceIssues() {
        return maintenanceService.getAllReportedIssues();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dashboard/open-issues")
    public ResponseEntity<?> getOpenIssues() {
        return maintenanceService.getOpenIssues();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/issues/{issueId}/update")
    public ResponseEntity<?> updateIssueStatus(@PathVariable Long issueId, @RequestBody MaintenanceIssueUpdateRequest request) {
        return maintenanceService.updateIssueStatus(issueId, request);
    }

    /** Count all issues not in RESOLVED status */
    @GetMapping("/issues/count/not-resolved")
    public ResponseEntity<?> getNotResolvedIssueCount() {
        return maintenanceService.countNotResolvedIssues();
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getUserNotifications(@RequestParam Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @PutMapping("/notifications/{id}/mark-read")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable String id) {
        try {
            Long notificationId = Long.parseLong(id);
            return notificationService.markNotificationAsRead(notificationId);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid notification ID: " + id);
        }
    }

}
