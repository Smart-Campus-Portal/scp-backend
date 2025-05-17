package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tut.scp.entity.Issue;
import tut.scp.enums.IssueStatus;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReporterId(Long reporterId);
    @Query("SELECT i.status, COUNT(i) FROM Issue i GROUP BY i.status")
    List<Object[]> countIssuesByStatus();
    List<Issue> findByStatus(IssueStatus status);
    // Count issues reported by a specific user
    long countByReporterId(Long reporterId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Issue i WHERE i.reporter.id = :userId")
    void deleteByUserId(Long userId);

    // New: count all issues whose status is NOT the given status
    long countByStatusNot(IssueStatus status);
}
