package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeegroupId;

import java.util.List;
import java.util.Optional;

public interface EmployeegroupRepository extends JpaRepository<Employeegroup, EmployeegroupId> {

    Optional<List<Employeegroup>> findByEmployeeId(String idEmployee);

    Optional<List<Employee>> findByGroupId(Integer groupId);
}