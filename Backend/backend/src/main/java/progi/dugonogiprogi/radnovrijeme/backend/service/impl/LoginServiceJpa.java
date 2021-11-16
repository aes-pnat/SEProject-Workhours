package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.service.LoginService;

import java.util.Optional;

@Service
public class LoginServiceJpa implements LoginService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public String logIn() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public String isLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
