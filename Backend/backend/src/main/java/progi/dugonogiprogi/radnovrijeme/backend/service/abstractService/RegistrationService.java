package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;

import java.util.List;

@Service
public interface RegistrationService {

    Employee registerEmployee(RegistrationDTO regData);

    List<Employee> getEmployees();

    String deleteEmployee(String pid);
}
