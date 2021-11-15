package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.Optional;


@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> user = employeeRepository.findByUsername(username);
        if(!user.isPresent())
            throw new UsernameNotFoundException("User with name: " + username + " does not exist.");
        return new EmployeeUserDetailsImpl(user.get());
    }
}
