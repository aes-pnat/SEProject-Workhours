package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.MyDataService;

/**
 * Handles requests for myData page
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/mydata")
public class MyDataController {

    /**
     * Injection of values of {@link MyDataService} properties
     */
    @Autowired
    MyDataService myDataService;

    /**
     * Responds to get request of the form /mydata?username="username_example"
     *
     * @return DTO of data belonging to an employee
     */

    @GetMapping("")
    public MyDataDTO myData(){
        return myDataService.myData();
    }
}
