package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.util.List;

@RestController
@RequestMapping("/workhours")
public class WorkHoursController {

    @Autowired
    WorkHoursService workHoursService;

    @Autowired
    EmployeeService employeeService;

    @Secured("ROLE_USER")
    @GetMapping("")
    public List<WorkHoursInput> listWorkHoursEmployee(@RequestBody String username) {
        return workHoursService.listWorkHoursEmployee(username);
    }

    @Secured("ROLE_OWNER")
    @GetMapping("/all")
    public List<WorkHoursInputDTO> listAllWorkHours() {
        return workHoursService.listAllWorkHours();
    }

    @Secured("ROLE_OWNER")
    @GetMapping("/select")
    public List<Employee> listAllEmployees() {
        return employeeService.listAllEmployees();
    }

    @Secured("ROLE_USER")
    @GetMapping("/select/{pid}")
    public WorkHoursInputDTO workHoursEmployee(@PathVariable String pid) {
        return workHoursService.workHoursEmployee(pid);
    }

    @Secured("ROLE_USER")
    @PostMapping("/add")
    public void inputWorkHours(@RequestBody WorkHoursInput workHoursInput) {
        workHoursService.inputWorkHours(workHoursInput);
    }
}
