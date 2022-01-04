package progi.dugonogiprogi.radnovrijeme.backend.rest;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;

import java.net.URI;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<Group, List<Employee>>> listAllGroups() {
        return ResponseEntity.ok().body(groupService.listAllGroups());
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteGroup(@RequestParam Integer groupId) {
        return ResponseEntity.ok().body(groupService.deleteGroup(groupId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> crateGroup(@Validated @RequestBody GroupDTO group) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/groups/add").toUriString());
        return ResponseEntity.created(uri).body(groupService.createGroup(group));
    }

}