package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> listAllEmployees();

}
