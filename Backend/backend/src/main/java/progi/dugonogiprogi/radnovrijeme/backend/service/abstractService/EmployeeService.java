package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> listAllEmployees();

}
