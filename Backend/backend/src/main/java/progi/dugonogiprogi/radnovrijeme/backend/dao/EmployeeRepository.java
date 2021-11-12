package progi.dugonogiprogi.radnovrijeme.backend.dao;

import org.springframework.stereotype.Repository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository {

    Employee findByUsername(String username);

    List<Employee> listAll();

    Employee findByIdEmployee(Long idEmployee);
}
