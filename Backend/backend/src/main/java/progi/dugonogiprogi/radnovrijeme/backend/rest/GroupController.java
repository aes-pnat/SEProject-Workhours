package progi.dugonogiprogi.radnovrijeme.backend.rest;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;

import java.util.List;
//import org.springframework.security.access.annotation.Secured;

/**
 *  Handles requests for the application Group page.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("")
    public List<Group> listAllGroups() {
        return groupService.listAllGroups();
    }

    @PostMapping("/delete")
    public void deleteGroup(@RequestParam Integer groupId) {
        groupService.deleteGroup(groupId);
    }

    @PostMapping("/add")
    public Group crateGroup(@Validated @RequestBody GroupDTO group) {
        return groupService.createGroup(group);
    }

}