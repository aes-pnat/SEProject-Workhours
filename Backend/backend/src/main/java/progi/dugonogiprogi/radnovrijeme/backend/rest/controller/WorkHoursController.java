package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.WorkHoursInputDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.EmployeeService;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/workhoursinput")
public class WorkHoursController {

    @Autowired
    WorkHoursService workHoursService;

    @PostMapping("")
    public Workhoursinput createNewWorkHoursInput(@RequestParam String task, @RequestParam LocalDate date, @RequestParam Integer hoursDone, @RequestParam Integer idEmployee) {
        return workHoursService.createNewWorkHoursInput(task, date, hoursDone, idEmployee);
    }

    @GetMapping("")
    public List<String> listTaskNamesForEmployee(@RequestParam String idEmployee) {
        return workHoursService.listTaskNamesForEmployee(idEmployee);
    }



}
