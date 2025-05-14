package tut.scp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tut.scp.enums.IssueCategory;
import tut.scp.enums.IssuePriority;
import tut.scp.enums.IssueStatus;
import tut.scp.enums.StudyRoomStatus;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Issue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User reporter;
    @Enumerated(EnumType.STRING)
    private IssueCategory category;
    @Enumerated(EnumType.STRING)
    private IssuePriority priority;
    private String description;
    private String location;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = IssueStatus.REPORTED;
    }
}
