package tut.scp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "timetable",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"time"})}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timetableID;

    private String name;
    private String course;
    private String location;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    @Column(unique = true)
    private String time;

    private String dayData = "no class";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
