package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;
//import org.springframework.security.access.annotation.Secured;


@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    //@Secured("OWNER")
    public Grupa createGroup(@RequestBody CreateGroupDTO dto) {
        return groupService.createGroup(dto.getName());
    }

    public Djelatnost assignJob(String workName, String workDescription) {
        return groupService.assignJob(workName,workDescription);
    }

    public Zadatak assignTask(String taskName) {
        return groupService.assignTask(taskName);
    }

    public Grupa edit(Grupa group){
        return groupService.edit(group);
    }

    public void delete(Grupa group) {
        groupService.delete(group);
    }


}