package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.RoleRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Role;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.EntityMissingException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.InvalidPasswordCheckException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.RegistrationService;
import java.util.Optional;

@Slf4j
@Service
public class RegistrationServiceJpa implements RegistrationService {

    @Autowired
    EmployeeRepository eRepository;

    @Autowired
    RoleRepository rRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public Employee registerEmployee(RegistrationDTO regData) {
        String user = BackendApplication.getUser();

        if(!regData.getPassword().equals(regData.getPasswordCheck())) {
            log.error("{}: Creating user failed: Password does not match password check", user);
            throw new InvalidPasswordCheckException("Password does not match password check.");
        }
        if(eRepository.findById(regData.getPid()).isPresent()) {
            log.error("{}: Creating user failed: Employee with that pid already exists", user);
            throw new IllegalArgumentException("Employee with that pid already exists.");
        }
        if(eRepository.findByUsername(regData.getUsername()).isPresent()) {
            log.error("{}: Creating user failed: Employee with username {} already exists", user, regData.getUsername());
            throw new IllegalArgumentException("Employee with username " + regData.getUsername() + " already exists.");
        }
        if(eRepository.findByEmail(regData.getEmail()).isPresent()){
            log.error("{}: Creating user failed: Employee with email {} already exists", user, regData.getEmail());
            throw new IllegalArgumentException("Employee with email " + regData.getEmail() + " already exists.");
        }
        Employee newEmployee = new Employee();
        newEmployee.setId(regData.getPid());
        newEmployee.setName(regData.getName());
        newEmployee.setSurname(regData.getSurname());
        newEmployee.setEmail(regData.getEmail());
        newEmployee.setUsername(regData.getUsername());
        newEmployee.setPassword(passwordEncoder.encode(regData.getPassword()));

        Optional<Role> r = rRepository.findByName("employee");
        if(r.isPresent()) {
            log.info("{}: Setting role successful: Adding role {} to user {}", user, r.get().getName(), newEmployee.getUsername());
            newEmployee.setIdrole(r.get());
        }
        else {
            log.error("{}: Setting role failed: Role with name employee does not exist", user);
            throw new EntityMissingException("Role with name employee does not exist.");
        }

        return eRepository.save(newEmployee);
    }
}
