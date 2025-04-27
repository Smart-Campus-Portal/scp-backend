package tut.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tut.scp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
