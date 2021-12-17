package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;
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
    public MyDataDTO myData(@RequestParam String username) throws NoSuchGroupException {
        return myDataService.myData(username);
    }








}
