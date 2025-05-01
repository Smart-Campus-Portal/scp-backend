package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReporterId(Long reporterId);
}
