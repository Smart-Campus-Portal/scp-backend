package tut.scp.dto;

import lombok.Getter;
import lombok.Setter;
import tut.scp.enums.IssueStatus;

@Getter
@Setter
public class MaintenanceIssueUpdateRequest {
    private IssueStatus status;
    private Long attendedBy;
}
