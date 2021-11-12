package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.TasksDTO;

import java.util.List;

@Service
public interface TaskService {

    List<TasksDTO> listTaskEmployee(String username);
}
