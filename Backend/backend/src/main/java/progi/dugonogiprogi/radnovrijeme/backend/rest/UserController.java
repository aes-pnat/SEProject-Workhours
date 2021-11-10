package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;
import progi.dugonogiprogi.radnovrijeme.backend.service.OwnerService;
//import org.springframework.security.access.annotation.Secured;


@RestController
public class UserController {

    @Autowired
    private OwnerService ownerService;

    //@Secured("OWNER")
    public Grupa createGroup(@RequestBody CreateGroupDTO dto) {
        return ownerService.createGroup(dto.getName());
    }

    public Djelatnost defineWork(String work) {
        return ownerService.defineWork(work);
    }





}