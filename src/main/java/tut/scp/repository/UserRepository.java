package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import tut.scp.entity.User;

import java.util.List;
=======
import tut.scp.entity.User;

>>>>>>> lecturer-local/lecturer
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
<<<<<<< HEAD
    @Query("SELECT u.role, COUNT(u) FROM User u GROUP BY u.role")
    List<Object[]> countUsersByRole();
=======
>>>>>>> lecturer-local/lecturer
}
