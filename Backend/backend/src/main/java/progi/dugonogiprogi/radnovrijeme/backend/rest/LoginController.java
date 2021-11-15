package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("")
    public Optional<Employee> getUser(UserDTO userDTO) {
        return loginService.getUser(userDTO.getUsername(), userDTO.getPassword());
    }
}
