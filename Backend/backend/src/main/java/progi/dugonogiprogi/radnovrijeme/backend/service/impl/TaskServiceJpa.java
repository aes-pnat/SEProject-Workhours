package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceJpa implements TaskService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<TasksDTO> listTaskEmployee(String username) {
        Employee employee = null;
        if (employeeRepository.findByUsername(username).isPresent())
            employee = employeeRepository.findByUsername(username).get();
        List<TasksDTO> returnList = new ArrayList<>();

        if (employee != null) {
            for (Group g : groupRepository.findAll()) {
                if (g.getMembers().contains(employee)) {
                    List<Task> tasksForGroup = new ArrayList<>();
                    for(Task t : employee.getTasks()) {
                        if(t.getBelongsTo().equals(g.getAssignedJob()))
                            tasksForGroup.add(t);
                    }
                    returnList.add(new TasksDTO(g.getName(), tasksForGroup));
                }
            }
        }

        return returnList;
    }
}
