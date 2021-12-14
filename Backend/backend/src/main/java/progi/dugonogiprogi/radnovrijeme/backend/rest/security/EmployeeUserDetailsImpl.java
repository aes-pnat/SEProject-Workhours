package progi.dugonogiprogi.radnovrijeme.backend.rest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import progi.dugonogiprogi.radnovrijeme.backend.dao.RoleRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class EmployeeUserDetailsImpl implements UserDetails {

    private Employee employee;

    public EmployeeUserDetailsImpl(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(employee.getIdrole() != null) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + employee.getRole().getName().toUpperCase(Locale.ROOT)));
            return authorities;
        }
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
