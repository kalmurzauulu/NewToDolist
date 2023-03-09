package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByIsDone(boolean isDone);
    List<Todo> findByIsRemove(boolean isRemove);
    List<Todo> findByPersonId(long id);
    List<Todo> findByPersonIdAndIsDoneTrueAndIsRemovedFalse(long userId);
    List<Todo> findByPersonIdAndIsDoneFalseAndIsRemovedFalse(long userId);

}