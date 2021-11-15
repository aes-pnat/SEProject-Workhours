package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeDao;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    public List<Employee> listAllEmployees();

    public Optional<Employee> getASingleEmployee(String employeeId);

    public Employee createEmployee(Employee employee);

    public void deleteEmployee(String employeeId);



}
