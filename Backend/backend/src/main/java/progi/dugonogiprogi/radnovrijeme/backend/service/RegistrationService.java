package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;

@Service
public interface RegistrationService {

    Employee registerEmployee(RegistrationDTO regData);
}
