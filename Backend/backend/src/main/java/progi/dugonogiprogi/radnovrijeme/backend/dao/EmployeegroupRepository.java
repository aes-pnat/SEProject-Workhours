package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeegroupId;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeetask;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeetaskId;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeegroupRepository extends JpaRepository<Employeegroup, EmployeegroupId> {

    Optional<List<Employeegroup>> findById_Idemployee(String idemployee);

    Optional<List<Employeegroup>> findById_Idgroup(Integer idgroup);

    Optional<List<Employee>> findByGroupId(Integer groupId);
}