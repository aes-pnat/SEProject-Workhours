package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("")
    public List<WorkHoursInput> listWorkHoursEmployee(@RequestBody String username) {
        return workHoursService.listWorkHoursEmployee(username);
    }

    //Secured direktor
    @GetMapping("/all")
    public List<WorkHoursInputDTO> listAllWorkHours() {
        return workHoursService.listAllWorkHours();
    }

    //Secured direktor
    @GetMapping("/select")
    public List<Employee> listAllEmployees() {
        return employeeService.listAllEmployees();
    }

    //Secured direktor
    @GetMapping("/select/{idEmployee}")
    public WorkHoursInputDTO workHoursEmployee(@PathVariable Long idEmployee) {
        return workHoursService.workHoursEmployee(idEmployee);
    }


    @PostMapping("/add")
    public void inputWorkHours(@RequestBody WorkHoursInput workHoursInput) {
        workHoursService.inputWorkHours(workHoursInput);
    }
}
