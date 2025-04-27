package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.StudyRoom;

import java.util.List;

public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {
    List<StudyRoom> findByIdNotIn(List<Long> ids);
}
