package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByUsername(username).get();
        if(user == null)
            throw new UsernameNotFoundException("User with name: " + username + " does not exist.");
        if(user.getRole().equals("owner")) {
            List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_OWNER"));
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    authorityList
            );
        }
        else if(user.getRole().equals("leader")) {
            List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_LEADER"));
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    authorityList
            );
        }
        else if(user.getRole().equals("employee")) {
            List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    authorityList
            );
        }
        else {
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    new ArrayList<GrantedAuthority>()
            );
        }
    }
}
