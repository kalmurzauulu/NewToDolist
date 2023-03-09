package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}