package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MyDataService;

/**
 * Handles requests for myData page
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/mydata")
public class MyDataController {

    
    @Autowired
    MyDataService myDataService;

    /**
     * Responds to get request for myData page
     *
     * @param username web request parameter containing employee's username
     * @return dto of data belonging to an employee
     */

    @GetMapping("")
    public MyDataDTO myData(@RequestParam String username){
        return myDataService.myData(username);
    }
}
