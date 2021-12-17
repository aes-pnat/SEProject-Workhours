package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.service.MyDataService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/mydata")
public class MyDataController {

    @Autowired
    MyDataService myDataService;
/*
    @GetMapping("")
    public List<String> listGroupNamesForEmployee(@RequestParam String idEmployee) {
        return myDataService.listGroupNamesForEmployee(idEmployee);
    }

    @GetMapping("")
    public List<Task> listTasksForEmployee(@RequestParam String idEmployee){
        return myDataService.listTasksForEmployee(idEmployee);
    }

    @GetMapping("")
    public Employee showDataForEmployee(@RequestParam String idEmployee) {
        return myDataService.showDataForEmployee(idEmployee);
    }
*/
}
