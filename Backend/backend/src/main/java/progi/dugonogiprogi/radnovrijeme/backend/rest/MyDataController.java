package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MyDataService;


@CrossOrigin("*")
@RestController
@RequestMapping("/mydata")
public class MyDataController {

    @Autowired
    MyDataService myDataService;


    @GetMapping("")
    public MyDataDTO myData(@RequestParam String username){
        return myDataService.myData(username);
    }
}
