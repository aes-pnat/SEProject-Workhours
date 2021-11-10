package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;
import progi.dugonogiprogi.radnovrijeme.backend.service.UserService;
//import org.springframework.security.access.annotation.Secured;


@RestController
public class GroupController {

    @Autowired
    private UserService userService;

    //@Secured("OWNER")
    public Grupa createGroup(@RequestBody CreateGroupDTO dto) {
        return userService.createGroup(dto.getName());
    }

    public Djelatnost defineWork(String workName, String workDescription) {
        return userService.defineWork(workName,workDescription);
    }

    public void registerUser(String name, String surname, String userName, String OIB, String email){
        userService.registerUser(name,surname,userName,OIB,email);
    }







}