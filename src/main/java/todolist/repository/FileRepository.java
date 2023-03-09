package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}