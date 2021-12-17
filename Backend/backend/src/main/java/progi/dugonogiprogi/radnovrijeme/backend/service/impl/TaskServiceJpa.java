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

        Employee leader = null;

        if (employeeRepository.findById(idLeader).isPresent())
            leader = employeeRepository.findById(idLeader).get();

        if (leader == null)
            return list;

        Optional<List<Group>> groups = groupRepository.findByIdleader(leader);
        if(!groups.isPresent()) {
            throw new NoSuchGroupException("Employee with id "+idLeader+"is not a leader");
        }
        List<Group> groupList = groups.get();

        for (Group g : groupList) {
            Optional<List<Employeegroup>> listEmployees = employeegroupRepository.findById_Idgroup(g.getId());
            if(!listEmployees.isPresent()){
                throw new MissingEmployeeException("The group with id"+ g.getId()+ "has no members");
            }
            for(Employeegroup eg : listEmployees.get()) {
                TasksDTO tasksDTO = new TasksDTO();
                Optional<List<Task>> taskss = taskRepository.findByIdjob_Id(g.getIdjob().getId());
                if(!taskss.isPresent()){
                    throw new NoSuchElementException("This group has none tasks set yet");
                }
                for (Task t : taskss.get()) {
                    tasksDTO.setTaskName(t.getName());
                    tasksDTO.setEstimatedDuration(t.getHoursneededestimate());
                    tasksDTO.setStartDateAndTime(t.getDatetimestart());
                    tasksDTO.setEndDateAndTime(t.getDatetimeend());
                    if (t.getIdlocation() != null) {
                        Optional<Location> loc = locationRepository.findById(t.getIdlocation().getId());
                        if(!loc.isPresent()){
                            throw new NoSuchElementException("Location with id "+t.getIdlocation().getId()+"doesn't exist");
                        }
                        Location l = loc.get();
                        tasksDTO.setLocation(l);
                    } else {
                        tasksDTO.setLocation(null);
                    }
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

            }
        }


        return list;
    }
}
