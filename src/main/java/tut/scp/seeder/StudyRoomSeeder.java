package tut.scp.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tut.scp.entity.StudyRoom;
import tut.scp.repository.StudyRoomRepository;

import java.util.List;

@Component
public class StudyRoomSeeder implements CommandLineRunner {

    private final StudyRoomRepository studyRoomRepo;

    @Autowired
    public StudyRoomSeeder(StudyRoomRepository studyRoomRepository) {
        this.studyRoomRepo = studyRoomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (studyRoomRepo.count() == 0) {
            List<StudyRoom> studyRooms = List.of(
                    new StudyRoom("Small Study Room", "Quiet room for up to 4 students.","Soshanguve South Campus Block C, Room C23", 4),
                    new StudyRoom("Large Study Room", "Group study room for up to 10 students.", "Soshanguve South Campus Block A, Room A30", 10),
                    new StudyRoom("Computer Lab", "Room with PCs for collaborative work.", "Soshanguve South Campus Block B, Lab B4", 50),
                    new StudyRoom("Conference Room", "Formal meeting room with projector.", "Soshanguve South Campus Block C, Room C11", 22)
            );
            studyRoomRepo.saveAll(studyRooms);
        }
    }
}
