package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddTaskDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddTaskInfoDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;

import java.util.List;

/**
 * Specifies how will tasks site process data and
 * what functionalities this service will provide.
 */
@Service
public interface TaskService {

    List<TasksDTO> listTasksForLeader(String idLeader);

    Task addTask(AddTaskDTO addTaskDTO);

    AddTaskInfoDTO getAddTaskInfo();
}
