package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tut.scp.entity.Issue;
import tut.scp.enums.IssueStatus;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReporterId(Long reporterId);
    @Query("SELECT i.status, COUNT(i) FROM Issue i GROUP BY i.status")
    List<Object[]> countIssuesByStatus();
    List<Issue> findByStatus(IssueStatus status);
}
