package co.edu.poli.attendancemarker.jpa.repository;

import co.edu.poli.attendancemarker.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUserName(String userName);

}
