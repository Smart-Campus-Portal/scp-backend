package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tut.scp.entity.Notification;
import tut.scp.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.expiresAt < :cutoff")
    void deleteExpiredNotifications(LocalDateTime cutoff);

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUserOrderByTimestampDesc(User user);
}
