package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.TasksDTO;

import java.util.List;

/**
 * Specifies how will tasks site process data and
 * what functionalities this service will provide.
 */
@Service
public interface TaskService {

    /**
     * This method gets data from repository and processes it into a data transfer object.
     * @return List of data transfer objects that contain all tasks
     * for authenticated user grouped by groups he is in.
     */
    List<TasksDTO> listTaskEmployee(String username);
}
