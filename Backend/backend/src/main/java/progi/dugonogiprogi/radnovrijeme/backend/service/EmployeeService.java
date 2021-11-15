package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    List<Employee> listAllEmployees();

    Optional<Employee> getASingleEmployee(String employeeId);

//  Employee updateEmployee(String employeeId, Employee employee);

    Employee createEmployee(Employee employee);

    void deleteEmployee(String employeeId);

//  viewOccupancy(String employeeId);

}
