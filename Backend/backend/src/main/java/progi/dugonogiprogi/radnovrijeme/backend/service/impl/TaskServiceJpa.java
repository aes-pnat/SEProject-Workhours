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

        Optional<Employee> lead = employeeRepository.findById(idLeader);

        if (!lead.isPresent())
            throw new MissingEmployeeException("Employee with id "+idLeader+ " not found");

        Employee leader = lead.get();

        Optional<List<Group>> groups = groupRepository.findByIdleader(leader);

        if(!groups.isPresent()) {
            throw new NoSuchGroupException("Employee with id "+idLeader+"is not a leader");
        }
        List<Group> groupList = groups.get();

        for (Group g : groupList) {

            Optional<List<Employeegroup>> listEmployees = employeegroupRepository.findById_Idgroup(g.getId());
            if (!listEmployees.isPresent()) {
                throw new MissingEmployeeException("The group with id" + g.getId() + "has no members");
            }
            Optional<List<Task>> taskss = taskRepository.findByIdjob_Id(g.getIdjob().getId());
            if (!taskss.isPresent()) {
                throw new NoSuchElementException("This group has none tasks set yet");
            }
            for (Task t : taskss.get()) {
                for (Employeegroup eg : listEmployees.get()) {
                    TasksDTO tasksDTO = new TasksDTO();
                    Optional<List<Employeetask>> et = employeetaskRepository.findById_Idemployee(eg.getId().getIdemployee());
                    if (!et.isPresent()) {
                        throw new NoSuchElementException("This employee hasn't been given any tasks");
                    }
                    for (Employeetask emt : et.get()) {
                        if (emt.getId().getIdtask().equals(t.getId())) {
                            Optional<Employee> employee = employeeRepository.findById(emt.getId().getIdemployee());
                            if (!employee.isPresent()) {
                                throw new MissingEmployeeException("Employee with id " + emt.getId().getIdemployee() + " doesn't exist");
                            }
                            Employee e = employee.get();
                            tasksDTO.setTaskName(t.getName());
                            tasksDTO.setEstimatedDuration(t.getHoursneededestimate());
                            tasksDTO.setStartDateAndTime(t.getDatetimestart());
                            tasksDTO.setEndDateAndTime(t.getDatetimeend());
                            tasksDTO.setEmployeeName(e.getName());
                            tasksDTO.setEmployeeSurname(e.getSurname());
                            tasksDTO.setLocation(t.getIdlocation());
                            tasksDTO.setJob(t.getIdjob());
                            list.add(tasksDTO);
                        }
                    }
                }

            }
        }
        return list;
    }
}
