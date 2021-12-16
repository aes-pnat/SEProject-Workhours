package progi.dugonogiprogi.radnovrijeme.backend.dao;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeegroupRepository {

    Optional<List<Employeegroup>> findById_Idemployee(String idemployee);

    Optional<List<Employeegroup>> findById_Idgroup(Integer idgroup);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeegroupId;

public interface EmployeegroupRepository extends JpaRepository<Employeegroup, EmployeegroupId> {
}
>>>>>>> c5e3810d174aa1d613d83ea64421cb9f32a0d1b3
