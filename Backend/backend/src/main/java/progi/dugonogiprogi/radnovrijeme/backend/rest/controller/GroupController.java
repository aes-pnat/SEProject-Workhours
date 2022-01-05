package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddGroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.GroupService;

import java.net.URI;
import java.util.List;

/**
 *  Handles requests for the application Group page.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Secured({"ROLE_OWNER"})
    @GetMapping("")
    public ResponseEntity<List<GroupDTO>> listAllGroups() {
        return ResponseEntity.ok().body(groupService.listAllGroups());
    }

    @Secured({"ROLE_OWNER"})
    @PostMapping("/delete")
    public ResponseEntity<?> deleteGroup(@RequestParam Integer groupId) {
        return ResponseEntity.ok().body(groupService.deleteGroup(groupId));
    }

    @Secured({"ROLE_OWNER"})
    @PostMapping("/add")
    public ResponseEntity<?> crateGroup(@Validated @RequestBody AddGroupDTO group) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/groups/add").toUriString());
        return ResponseEntity.created(uri).body(groupService.createGroup(group));
    }

}