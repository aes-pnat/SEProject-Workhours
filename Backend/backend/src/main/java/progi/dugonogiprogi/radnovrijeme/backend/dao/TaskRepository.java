package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

/**
 * Repository for tasks in a company.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
