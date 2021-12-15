package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;

@Service
public interface RegistrationService {

    void registerEmployee(RegistrationDTO regData);
}
