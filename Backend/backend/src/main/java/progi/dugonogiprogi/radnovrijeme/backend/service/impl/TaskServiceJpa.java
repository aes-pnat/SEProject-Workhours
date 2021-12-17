package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.TasksDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchGroupException;
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

        Optional<Group> group = groupRepository.findByIdleader_Id(idLeader);
        if(!group.isPresent()) {
            throw new NoSuchGroupException("Employee with id "+idLeader+"is not a leader");
        }
        Group g = group.get();
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
            Optional<Location> loc = locationRepository.findById(t.getIdlocation().getId());
            if(!loc.isPresent()){
                throw new NoSuchElementException("Location with id "+t.getIdlocation().getId()+"doesn't exist");
            }
            Location l = loc.get();
            tasksDTO.setLocation(l);
            Optional<Job> j = jobRepository.findById(t.getIdjob().getId());
            if(!j.isPresent()) {
                throw new NoSuchElementException("Job with id "+t.getIdjob().getId()+"doesn't exist");
            }
            Job job = j.get();
            tasksDTO.setJob(job);
            Optional<List<Employeetask>> et = employeetaskRepository.findById_Idemployee(eg.getId().getIdemployee());
            if(!et.isPresent()) {
                throw new NoSuchElementException("This employee hasn't been given any tasks");
            }
            for(Employeetask emt : et.get()){
                if(emt.getId().getIdtask().equals(t.getId())){
                    Optional<Employee> employee = employeeRepository.findById(emt.getId().getIdemployee());
                    if(!employee.isPresent()) {
                        throw new MissingEmployeeException("Employee with id "+emt.getId().getIdemployee()+"doesn't exist");
                    }
                    Employee e = employee.get();
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
