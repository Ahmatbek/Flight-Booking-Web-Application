package kg.attractor.projects.controlwork9.repository;

import kg.attractor.projects.controlwork9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByAuthorityId(long id);
}
