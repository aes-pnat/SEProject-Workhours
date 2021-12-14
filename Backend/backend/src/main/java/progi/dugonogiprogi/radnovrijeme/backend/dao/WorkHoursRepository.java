package progi.dugonogiprogi.radnovrijeme.backend.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;

import java.util.List;

/**
 * Repository for work hours inputs in a company.
 */
@Repository
public interface WorkHoursRepository extends JpaRepository<Workhoursinput, Long> {
}
