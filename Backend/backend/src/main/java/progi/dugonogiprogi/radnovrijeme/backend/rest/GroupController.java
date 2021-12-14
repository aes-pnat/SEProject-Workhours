package progi.dugonogiprogi.radnovrijeme.backend.rest;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
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

    @GetMapping("")


}