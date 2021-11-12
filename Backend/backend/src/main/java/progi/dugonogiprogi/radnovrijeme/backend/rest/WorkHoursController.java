package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.util.List;

@RestController
@RequestMapping("/workhours")
public class WorkHoursController {

    @Autowired
    WorkHoursService workHoursService;

    @GetMapping("")
    public List<WorkHoursInput> listWorkHoursEmployee(@RequestBody String username) {
        return workHoursService.listWorkHoursEmployee(username);
    }

    //Secured direktor
    @GetMapping("/all")
    public List<WorkHoursInput> listAllWorkHours() {
        return null;
    }

    @PostMapping("/add")
    public void inputWorkHours(@RequestBody WorkHoursInput workHoursInput) {

    }
}
