package progi.dugonogiprogi.radnovrijeme.backend.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddTaskDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddTaskInfoDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.TaskService;

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
     * @return list of DTOs representing tasks given to members of a group whose leader is an employee with given id
     */

    @GetMapping("")
    public List<?> listTasksForLeader(@RequestParam String groupName){
        return taskService.listTasksForLeader(groupName);
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
