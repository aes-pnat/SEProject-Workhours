package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

@Repository
public interface WorkHoursRepository extends JpaRepository<WorkHoursInput, Long> {


}
