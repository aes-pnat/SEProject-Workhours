package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.RoleRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.RequiredDataException;
import progi.dugonogiprogi.radnovrijeme.backend.service.RegistrationService;

@Service
public class RegistrationServiceJpa implements RegistrationService {

    @Autowired
    EmployeeRepository eRepository;

    @Autowired
    RoleRepository rRepository;

    //TODO: password encoder i spremati kriptirane sifre kad se doda security

    @Override
    public void registerEmployee(RegistrationDTO regData) {
        if(regData.getPid() == null)
            throw new RequiredDataException("Pid should not be empty.");

        if(regData.getName() == null)
            throw new RequiredDataException("Name should not be empty.");

        if(regData.getSurname() == null)
            throw new RequiredDataException("Surname should not be empty.");

        if(regData.getEmail() == null)
            throw new RequiredDataException("Email should not be empty.");

        if(regData.getUsername() == null)
            throw new RequiredDataException("Username should not be empty.");

        if(regData.getPassword() == null)
            throw new RequiredDataException("Password should not be empty.");

        if(regData.getPasswordCheck() == null)
            throw new RequiredDataException("Password check should not be empty.");

        if(!regData.getPassword().equals(regData.getPasswordCheck()))
            throw new IllegalArgumentException("Password did not match password check.");

        if(eRepository.getByUsername(regData.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Employee with pid " + regData.getPid() + " already exists");
        }

        if(eRepository.getByUsername(regData.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Employee with username " + regData.getUsername() + " already exists");
        }

        if(eRepository.getByEmail(regData.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Employee with email " + regData.getEmail() + " already exists");
        }

        Employee newEmployee = new Employee();

        newEmployee.setId(regData.getPid());
        newEmployee.setName(regData.getName());
        newEmployee.setSurname(regData.getSurname());
        newEmployee.setEmail(regData.getEmail());
        newEmployee.setUsername(regData.getUsername());
        newEmployee.setPassword(regData.getPassword());
        newEmployee.setIdrole(rRepository.getByName("employee").get());

        eRepository.save(newEmployee);
    }
}
