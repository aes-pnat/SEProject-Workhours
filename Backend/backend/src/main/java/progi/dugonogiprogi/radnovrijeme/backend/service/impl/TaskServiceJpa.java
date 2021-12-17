package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.service.TaskService;

import java.util.*;

@Service
public class TaskServiceJpa implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    EmployeetaskRepository employeetaskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeegroupRepository employeegroupRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    JobRepository jobRepository;


    @Override
    public List<TasksDTO> listTasksForLeader(String idLeader) {
        List<TasksDTO> list = new LinkedList<>();

        Group g = groupRepository.findByIdleader_Id(idLeader);

        Optional<List<Employeegroup>> listEmployees = employeegroupRepository.findById_Idgroup(g.getId());
        if(!listEmployees.isPresent()){
            throw new MissingEmployeeException("The group with id"+ g.getId()+ "has no members");
        }
        for(Employeegroup eg : listEmployees.get()) {
            TasksDTO tasksDTO = new TasksDTO();
            Optional<Task> task = taskRepository.findByIdjob_Id(g.getIdjob().getId());
            if(!task.isPresent()){
                throw new NoSuchElementException("This group has none tasks set yet");
            }
            Task t = task.get();
            tasksDTO.setTaskName(t.getName());
            tasksDTO.setEstimatedDuration(t.getHoursneededestimate());
            tasksDTO.setStartDateAndTime(t.getDatetimestart());
            tasksDTO.setEndDateAndTime(t.getDatetimeend());
            Location l = locationRepository.getById(t.getIdlocation().getId());
            tasksDTO.setLocation(l);
            Job job = jobRepository.getById(t.getIdjob().getId());
            tasksDTO.setJob(job);
            Optional<List<Employeetask>> et = employeetaskRepository.findById_Idemployee(eg.getId().getIdemployee());
            if(!et.isPresent()) {
                throw new NoSuchElementException("This employee hasn't been given any tasks");
            }
            for(Employeetask emt : et.get()){
                if(emt.getId().getIdtask().equals(t.getId())){
                    Employee e = employeeRepository.getById(emt.getId().getIdemployee());
                    tasksDTO.setEmployeeName(e.getName());
                    tasksDTO.setEmployeeSurname(e.getSurname());
                    break;
                }
            }
            list.add(tasksDTO);
        }
        return list;
    }
}
