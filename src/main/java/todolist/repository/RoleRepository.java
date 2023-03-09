package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}