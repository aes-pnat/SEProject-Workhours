package progi.dugonogiprogi.radnovrijeme.backend.rest.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.RoleRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeDetailsService implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findByUsername(username);
        if(!optionalEmployee.isPresent()) {
            log.error("User with username {} does not exist.", username);
            throw new UsernameNotFoundException("User with username " + username + " does not exist.");
        }
        else {
            log.info("User found in database: {}", username);
        }
        Employee employee = optionalEmployee.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + employee.getIdrole().getName().toUpperCase()));
        return new User(employee.getUsername(), employee.getPassword(), authorities);
    }
}
