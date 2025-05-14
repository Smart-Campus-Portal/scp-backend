package tut.scp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;



import java.sql.Time;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timetableID;

    private String name;
    private String course;
    private String day;
    private Time time;
    private String dayData;
    
    // Many-to-One Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable =  false)
    @JsonIgnore
    private User user;
}
