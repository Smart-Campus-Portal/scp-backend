package tut.scp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class NotificationResponse {
    private Long id; // Add id field
    private String message;
    private LocalDateTime timestamp;
    private boolean read;

    // Constructor with all fields
    public NotificationResponse(Long id, String message, LocalDateTime timestamp, boolean read) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.read = read;
    }
}
