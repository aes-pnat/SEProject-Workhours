package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.RegistrationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("")
    public void registerEmployee(@RequestBody RegistrationDTO regData) {
        registrationService.registerEmployee(regData);
    }
}