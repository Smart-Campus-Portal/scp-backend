package tut.scp.shared.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tut.scp.dto.NotificationResponse;
import tut.scp.entity.Notification;
import tut.scp.entity.User;
import tut.scp.repository.NotificationRepository;
import tut.scp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getNotificationsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        List<Notification> notifications = notificationRepository.findByUserOrderByTimestampDesc(user.get());

        List<NotificationResponse> dtoList = notifications.stream()
                .map(n -> new NotificationResponse(n.getId(), n.getMessage(), n.getTimestamp(), n.isRead()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<?> markNotificationAsRead(Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);

        if (optionalNotification.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }

        Notification notification = optionalNotification.get();
        notification.setRead(true);
        notificationRepository.save(notification);

        return ResponseEntity.ok("Notification marked as read.");
    }
}
