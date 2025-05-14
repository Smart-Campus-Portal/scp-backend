package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tut.scp.entity.StudyRoomBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyRoomBookingRepository extends JpaRepository<StudyRoomBooking, Long> {

    @Query("SELECT srb.room.id FROM StudyRoomBooking srb " +
            "WHERE srb.status = 'BOOKED' " +
            "AND NOT (srb.endTime <= :startTime OR srb.startTime >= :endTime)")
    List<Long> findBookedRoomIds(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT srb.status, COUNT(srb) FROM StudyRoomBooking srb " +
            "GROUP BY srb.status")
    List<Object[]> countStudyRoomBookingsByStatus();

    @Query("SELECT srb.room.name, COUNT(srb) " +
            "FROM StudyRoomBooking srb " +
            "GROUP BY srb.room.name " +
            "ORDER BY COUNT(srb) DESC")
    List<Object[]> findTopBookedStudyRooms();

}
