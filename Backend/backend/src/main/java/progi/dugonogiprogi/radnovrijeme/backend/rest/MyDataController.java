package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.service.MyDataService;
import progi.dugonogiprogi.radnovrijeme.backend.service.impl.NoSuchGroupException;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("/mydata")
public class MyDataController {

    @Autowired
    MyDataService myDataService;

    @GetMapping("")
    public List<String> listGroupNamesForEmployee(@RequestParam String idEmployee) throws NoSuchGroupException {
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








}
