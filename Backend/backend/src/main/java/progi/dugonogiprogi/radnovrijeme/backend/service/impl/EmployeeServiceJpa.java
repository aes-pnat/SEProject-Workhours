package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.OccupancyDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class EmployeeServiceJpa implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee getASingleEmployee(String employeeUsername) {
        return employeeRepository.findByUsername(employeeUsername);
    }

//    @Override
//    public Employee updateEmployee(String employeeId, Employee employee) {
//        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
//        if (!existingEmployee.isEmpty()) {
//        }
//        return null;
//    }

    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public OccupancyDTO viewOccupancy(String employeeId){
        Employee employee = employeeRepository.getById(employeeId);
        Set<Task> tasks = employee.getTasks();
        List<OccupancyDTO.Interval> intervals = new ArrayList<>();

        for (Task task : tasks) {
            OccupancyDTO.Interval interval = new OccupancyDTO.Interval(task.getDateTimeStart(), task.getDateTimeEnd());
            intervals.add(interval);
        }

        return new OccupancyDTO(intervals, employeeId);
    }

}
