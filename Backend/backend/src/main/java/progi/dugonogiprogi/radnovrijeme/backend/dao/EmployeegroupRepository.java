package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeegroupRepository {

    Optional<List<Employeegroup>> findById_Idemployee(String idemployee);

    Optional<List<Employeegroup>> findById_Idgroup(Integer idgroup);
}

