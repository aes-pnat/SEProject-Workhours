package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeDao;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceJpa implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getASingleEmployee(String employeeId) {
        return employeeRepository.findByPid(employeeId);
    }

    @Override
    public Optional<Employee> updateEmployee(String employeeId, EmployeeDao employeeDao) {
        Optional<Employee> employee = employeeRepository.findByPid(employeeId);
        if (!employee.isEmpty()){
        }
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employee){
        return null;
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }



}
