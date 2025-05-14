package tut.scp.entity;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> lecturer-local/lecturer
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tut.scp.enums.Role;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "\"user\"")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "is_enabled")
    private boolean isEnabled;

<<<<<<< HEAD
=======
      // One-to-Many Relationship
      @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<LecturerSchedule> lecturerSchedules;
      
      @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Timetable> timetables;
  
    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentsAsLecturer;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentsAsStudent;

      
>>>>>>> lecturer-local/lecturer
}
