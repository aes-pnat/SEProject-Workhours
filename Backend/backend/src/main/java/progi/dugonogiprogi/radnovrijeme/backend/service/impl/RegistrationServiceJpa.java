package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.RoleRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Role;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.EntityMissingException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.InvalidPasswordCheckException;
import progi.dugonogiprogi.radnovrijeme.backend.service.RegistrationService;
import java.util.Optional;

@Slf4j
@Service
public class RegistrationServiceJpa implements RegistrationService {

    @Autowired
    EmployeeRepository eRepository;

    @Autowired
    RoleRepository rRepository;

    //TODO: password encoder i spremati kriptirane sifre kad se doda security

    @Override
    public Employee registerEmployee(RegistrationDTO regData) {
        log.info("Adding new user {} to the database.", regData.getUsername());
        if(!regData.getPassword().equals(regData.getPasswordCheck())) {
            log.error("Password does not match password check.");
            throw new InvalidPasswordCheckException("Password does not match password check.");
        }
        if(eRepository.findById(regData.getPid()).isPresent()) {
            log.error("Employee with pid {} already exists", regData.getPid());
            throw new IllegalArgumentException("Employee with pid " + regData.getPid() + " already exists");
        }
        if(eRepository.findByUsername(regData.getUsername()).isPresent()) {
            log.error("Employee with username {} already exists", regData.getUsername());
            throw new IllegalArgumentException("Employee with username " + regData.getUsername() + " already exists");
        }
        if(eRepository.findByEmail(regData.getEmail()).isPresent()){
            log.error("Employee with email {} already exists", regData.getEmail());
            throw new IllegalArgumentException("Employee with email " + regData.getEmail() + " already exists");
        }
        Employee newEmployee = new Employee();
        newEmployee.setId(regData.getPid());
        newEmployee.setName(regData.getName());
        newEmployee.setSurname(regData.getSurname());
        newEmployee.setEmail(regData.getEmail());
        newEmployee.setUsername(regData.getUsername());
        newEmployee.setPassword(regData.getPassword());
        Optional<Role> r = rRepository.findByName("employee");
        if(r.isPresent()) {
            log.info("Adding role {} to user {}.", r.get(), newEmployee.getUsername());
            newEmployee.setIdrole(r.get());
        }
        else {
            log.error("Role employee does not exist.");
            throw new EntityMissingException("Role employee does not exist.");
        }
        return eRepository.save(newEmployee);
    }
}
