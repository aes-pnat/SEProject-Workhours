package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

}
