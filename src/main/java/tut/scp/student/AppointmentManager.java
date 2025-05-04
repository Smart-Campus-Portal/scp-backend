package tut.scp.student;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentManager implements AppointmentInterface {

    @Override
    public ResponseEntity<?> makeAppointment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeAppointment'");
    }

    @Override
    public ResponseEntity<?> viewAppointments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewAppointments'");
    }

    @Override
    public ResponseEntity<?> searchAppointments(Long searhKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchAppointments'");
    }

    
}
