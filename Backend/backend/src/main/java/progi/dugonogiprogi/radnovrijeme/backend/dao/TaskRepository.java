package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.List;
import java.util.Optional;

/**
 * Repository for tasks in a company.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById_Idjob(Integer idjob);
}
