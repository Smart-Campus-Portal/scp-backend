
 package tut.scp.student;

import org.springframework.http.ResponseEntity;

public interface AppointmentInterface {

    ResponseEntity<?>  makeAppointment();
    ResponseEntity<?>  viewAppointments();
    ResponseEntity<?>  searchAppointments(Long searchKey);


    
}