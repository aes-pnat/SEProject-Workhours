package progi.dugonogiprogi.radnovrijeme.backend.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

import java.util.List;

@Repository
public interface WorkHoursRepository extends JpaRepository<WorkHoursInput, Long> {

    List<WorkHoursInput> findByHasDoneEquals(Employee employee);
}
