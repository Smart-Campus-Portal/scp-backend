package tut.scp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime timestamp;
    private LocalDateTime expiresAt; // Expiry time
    @Column(name = "`read`") // escape reserved keyword
    private boolean read;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
