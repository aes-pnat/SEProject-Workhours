package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> getUser(String username, String password) {
        Optional<Employee> e = employeeRepository.findByUsername(username);
        if(e.get().getPassword().equals(password))
            return e;
        return null;
    }
}
