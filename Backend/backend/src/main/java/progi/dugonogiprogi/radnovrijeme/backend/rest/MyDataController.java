package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeetaskRepository;
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

    /**
     * Injection of values of {@link MyDataService} properties
     */
    @Autowired
    MyDataService myDataService;

    /**
     * Responds to get request of the form /mydata?username="username_example"
     *
     * @param username web request parameter representing employee's username
     * @return DTO of data belonging to an employee
     */

    @GetMapping("")
    public MyDataDTO myData(@RequestParam String username){
        return myDataService.myData(username);
    }
}
