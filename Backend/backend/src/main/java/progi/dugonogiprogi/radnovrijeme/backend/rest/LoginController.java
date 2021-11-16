package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.service.impl.LoginServiceJpa;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    LoginServiceJpa loginServiceJpa;

    @GetMapping("")
    public String isLoggedIn() {
        return loginServiceJpa.isLoggedIn();
    }

    @PostMapping("")
    public String logIn() {
        System.out.println(loginServiceJpa.logIn());
        return loginServiceJpa.logIn();
    }
}
