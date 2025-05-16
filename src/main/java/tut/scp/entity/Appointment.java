package tut.scp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tut.scp.entity.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Time time;
    private String location;
    private String description;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "module_name")
    private String moduleName;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private User student;
    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    @JsonIgnore
    private User lecturer;
    private boolean isAccepted;

}
