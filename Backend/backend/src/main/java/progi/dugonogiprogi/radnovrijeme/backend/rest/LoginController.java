package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("")
    public String isLoggedIn() {
        return loginService.isLoggedIn();
    }

    @PostMapping("")
    public String logIn() {
        return loginService.logIn();
    }
}
