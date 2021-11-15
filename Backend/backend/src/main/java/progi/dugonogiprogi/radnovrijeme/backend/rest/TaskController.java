package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;

import java.util.List;

/**
 * REST controller for handling web applications tasks site requests.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * This method returns tasks that authenticated user has to do grouped by groups that he is in.
     * @return List of data transfer objects.
     */
    @GetMapping("")
    public List<TasksDTO> listTasksEmployee() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.listTaskEmployee(userDetails.getUsername());
    }
}
