package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;

import java.util.List;

/**
 * REST controller for handling web applications tasks site requests.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("")
    public List<TasksDTO> listTasksForLeader(@RequestParam String idLeader){
        return taskService.listTasksForLeader(idLeader);
    }

}
