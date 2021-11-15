package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String logIn() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String isLoggedIn() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals(null))
            return null;
        return username;
    }
}
