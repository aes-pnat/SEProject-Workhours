package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.RegistrationService;
import java.net.URI;


@CrossOrigin("*")
@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("")
    public ResponseEntity<?> registerEmployee(@Validated @RequestBody RegistrationDTO regData) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(registrationService.registerEmployee(regData));
    }
}
