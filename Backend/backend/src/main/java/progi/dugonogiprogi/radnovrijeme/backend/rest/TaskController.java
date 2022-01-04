package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddTaskDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddTaskInfoDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * Responds to get request of the form /tasks?idLeader="idLeader_example"
     *
     * @param idLeader web request parameter representing leader's id
     * @return list of DTOs representing tasks given to members of a group whose leader is an employee with given id
     */
    @GetMapping("")
    public List<TasksDTO> listTasksForLeader(@RequestParam String idLeader){
        return taskService.listTasksForLeader(idLeader);
    }

    @PostMapping("/add")
    public Task addTask(@RequestBody AddTaskDTO addTaskDTO) {
        return taskService.addTask(addTaskDTO);
    }

    @GetMapping("/add")
    public AddTaskInfoDTO getAddTaskInfo() {
        return taskService.getAddTaskInfo();
    }

}
