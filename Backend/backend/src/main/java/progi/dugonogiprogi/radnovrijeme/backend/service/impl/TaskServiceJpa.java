package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchGroupException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.TaskService;

import java.util.*;

@Slf4j
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
    public List<TasksDTO> listTasksForLeader() {
        String user = BackendApplication.getUser();
        List<TasksDTO> list = new LinkedList<>();

        Optional<Employee> lead = employeeRepository.findByUsername(user);

        if (lead.isEmpty()) {
            throw new MissingEmployeeException("Employee with username " + user + " not found");
        }
        Employee leader = lead.get();

        List<Group> groups = groupRepository.findByIdleader_Id(leader.getId());
        if(groups.isEmpty()) {
            throw new NoSuchGroupException("Employee with username " + user + "is not a leader");
        }

        for (Group g : groups) {
            List<Employeegroup> employeeGroups = employeegroupRepository.findById_Idgroup(g.getId());
            if (employeeGroups.isEmpty()) {
                throw new MissingEmployeeException("The group with id" + g.getId() + "has no members");
            }
            List<Employee> employees = new ArrayList<>();
            for (Employeegroup employeegroup : employeeGroups) {
                Optional<Employee> temp = employeeRepository.findById(employeegroup.getId().getIdemployee());
                temp.ifPresent(employees::add);
            }

            for (Employee employee : employees) {
                List<Employeetask> employeetasks = employeetaskRepository.findById_Idemployee(employee.getId());

                List<Task> tasks = new ArrayList<>();
                for (Employeetask employeetask : employeetasks) {
                    Optional<Task> temp = taskRepository.findById(employeetask.getId().getIdtask());
                    temp.ifPresent(tasks::add);
                }

                for (Task task : tasks) {
                    if (task.getIdjob().getId().equals(g.getIdjob().getId())) {
                        TasksDTO tasksDTO = new TasksDTO();
                        tasksDTO.setEmployeeName(employee.getName());
                        tasksDTO.setEmployeeSurname(employee.getSurname());
                        tasksDTO.setLocation(task.getIdlocation());
                        tasksDTO.setEstimatedDuration(task.getHoursneededestimate());
                        tasksDTO.setJob(task.getIdjob());
                        tasksDTO.setTaskName(task.getName());
                        tasksDTO.setStartDateAndTime(task.getDatetimestart());
                        tasksDTO.setEndDateAndTime(task.getDatetimeend());
                        tasksDTO.setTaskGroup(g);
                        list.add(tasksDTO);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public Task addTask(AddTaskDTO addTaskDTO) {
        String user = BackendApplication.getUser();
        Integer locID = addTaskDTO.getLocationID();
        Location loc;
        if (locID == null) {
            loc = new Location();
            loc.setAddress(addTaskDTO.getNewLocationAddress());
            loc.setPlacename(addTaskDTO.getNewLocationPlaceName());
            loc.setLatitude(addTaskDTO.getNewLocationLatitude());
            loc.setLongitude(addTaskDTO.getNewLocationLongitude());
            locationRepository.save(loc);
            log.info("{}: Creating location successful: Created location with address {}", user, addTaskDTO.getNewLocationAddress());
        }
        else {
            if (locationRepository.findById(locID).isEmpty())
                throw new IllegalArgumentException("Location with ID " + locID + " doesn't exist!");
            loc = locationRepository.findById(locID).get();
        }
        Integer jobID = addTaskDTO.getJobID();
        if (jobRepository.findById(addTaskDTO.getJobID()).isEmpty())
            throw new IllegalArgumentException("Job with ID " + jobID + " doesn't exist!");
        Job job = jobRepository.findById(addTaskDTO.getJobID()).get();
        Task task = new Task(addTaskDTO.getTaskName(), addTaskDTO.getTaskDescription(), addTaskDTO.getDateStart().toInstant(), addTaskDTO.getDateEnd().toInstant(), addTaskDTO.getHoursEstimate(), job, loc);
        taskRepository.save(task);
        if(locID == null) {
            log.info("{}: Creating task successful: Created task with id {}", user, task.getId());
        }
        else {
            log.info("{}: Creating task successful: Created task with id {} at location {}", user, task.getId(), loc.getAddress());
        }

        for (String id : addTaskDTO.getEmployeeIDs()) {
            EmployeetaskId employeetaskId = new EmployeetaskId();
            employeetaskId.setIdemployee(id);
            employeetaskId.setIdtask(task.getId());
            Employeetask employeetask = new Employeetask();
            employeetask.setId(employeetaskId);
            employeetaskRepository.save(employeetask);
        }
        return task;
    }

    @Override
    public AddTaskInfoDTO getAddTaskInfo() {
        List<EmployeeDTO> employees = new ArrayList<>();
        List<LocationDTO> locations = new ArrayList<>();
        List<JobDTO> jobs = new ArrayList<>();

        for (Employee e : employeeRepository.findAll())
            employees.add(new EmployeeDTO(e.getName() + " " + e.getSurname(), e.getId()));

        for (Location l : locationRepository.findAll())
            locations.add(new LocationDTO(l.getAddress() + ", " + l.getPlacename(), l.getId()));

        for (Job j : jobRepository.findAll())
            jobs.add(new JobDTO(j.getName(), j.getId()));

        return new AddTaskInfoDTO(employees, locations, jobs);
    }
}
