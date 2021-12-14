package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.RegistrationDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.RequiredDataException;
import progi.dugonogiprogi.radnovrijeme.backend.service.RegistrationService;

@Service
public class RegistrationServiceJpa implements RegistrationService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public void registerEmployee(RegistrationDTO regData) {
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

        if(repository.getByUsername(regData.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Employee with username " + regData.getUsername() + " already exists");
        }

        if(repository.getByEmail(regData.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Employee with email " + regData.getEmail() + " already exists");
        }

        Employee newEployee = new Employee();
        newEployee.setName();
    }
}
