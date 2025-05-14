package tut.scp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import tut.scp.enums.IssueCategory;
import tut.scp.enums.IssuePriority;
import tut.scp.enums.IssueStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class IssueRequest {
    private Long reporterId;
    private IssueCategory category;
    private IssuePriority priority;
    private String description;
    private String location;
    private IssueStatus status;
    private LocalDateTime createdAt;
}
