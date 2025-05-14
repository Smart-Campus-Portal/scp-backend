package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tut.scp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    @Query("SELECT u.role, COUNT(u) FROM User u " +
            "GROUP BY u.role")
    List<Object[]> countUsersByRole();
}
