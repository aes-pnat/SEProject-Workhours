package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.OccupancyDTO;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    public List<Employee> listAllEmployees();

}
