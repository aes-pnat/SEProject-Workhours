package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<?> listTasksForLeader(String groupName) {
        String user = BackendApplication.getUser();

        if(groupName.equals("-1")) {
            List<Group> groups = groupRepository.findByIdleader_Username(user);
            List<String> groupNames = new ArrayList<>();
            for(Group group : groups) {
                groupNames.add(group.getName());
            }
            return groupNames;
        }


        List<TasksDTO> list = new LinkedList<>();

        Optional<Employee> lead = employeeRepository.findByUsername(user);

        if (lead.isEmpty()) {
            throw new MissingEmployeeException("Employee with username " + user + " not found");
        }
        Employee leader = lead.get();

        Optional<Group> optionalGroup = groupRepository.findByName(groupName);
        if(optionalGroup.isEmpty()) {
            throw new NoSuchGroupException("Group with name " + groupName + " does not exist.");
        }
        Group group = optionalGroup.get();
        if(!group.getIdleader().getUsername().equals(user)) {
            throw new NoSuchGroupException("Employee with username " + user + "is not a leader of group " + groupName + ".");
        }

        List<Employeegroup> employeeGroups = employeegroupRepository.findById_Idgroup(group.getId());
        if (employeeGroups.isEmpty()) {
            throw new MissingEmployeeException("The group with id" + group.getId() + "has no members");
        }
        List<Employee> employees = new ArrayList<>();
        employees.add(leader);
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
                if (task.getIdjob().getId().equals(group.getIdjob().getId())) {
                    TasksDTO tasksDTO = new TasksDTO();
                    tasksDTO.setEmployeeName(employee.getName());
                    tasksDTO.setEmployeeSurname(employee.getSurname());
                    tasksDTO.setLocation(task.getIdlocation());
                    tasksDTO.setEstimatedDuration(task.getHoursneededestimate());
                    tasksDTO.setJob(task.getIdjob());
                    tasksDTO.setTaskName(task.getName());
                    tasksDTO.setStartDateAndTime(task.getDatetimestart());
                    tasksDTO.setEndDateAndTime(task.getDatetimeend());
                    tasksDTO.setTaskGroup(group);
                    list.add(tasksDTO);
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
            if (locationRepository.findById(locID).isEmpty()) {
                log.error("{}: Creating task failed: Location with id {} does not exist", user, locID);
                throw new IllegalArgumentException("Location with ID " + locID + " doesn't exist!");
            }
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
    public AddTaskInfoDTO getAddTaskInfo(String groupName) {
        String username = BackendApplication.getUser();
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if(employee.isEmpty()){
            throw new MissingEmployeeException("Employee with username " +username+ " does not exist");
        }
        Employee e = employee.get();

        if (groupName.equals("-1")) {
            List<Group> groups = groupRepository.findByIdleader_Id(e.getId());
            AddTaskInfoDTO addTaskInfoDTO = new AddTaskInfoDTO();
            addTaskInfoDTO.setGroups(groups);
            return addTaskInfoDTO;
        } else {
            Group g = groupRepository.findByName(groupName).get();
            List<Employee> employees = new ArrayList<>();
            List<Employeegroup> employeegroups = employeegroupRepository.findById_Idgroup(g.getId());

            for (Employeegroup eg : employeegroups) {
                Optional<Employee> emp = employeeRepository.findById(eg.getId().getIdemployee());
                if (emp.isPresent()) {
                    employees.add(emp.get());
                }
            }

            List<LocationDTO> locations = new ArrayList<>();
            List<JobDTO> jobs = new ArrayList<>();
            Job job = groupRepository.findById(g.getId()).get().getIdjob();
            jobs.add(new JobDTO(job.getName(), job.getId()));

            List<EmployeeDTO> empDTOs = new ArrayList<>();

            for (Employee e1 : employees) {
                empDTOs.add(new EmployeeDTO(e.getName() + " " + e.getSurname(), e.getId()));
            }

            for (Location l : locationRepository.findAll())
                locations.add(new LocationDTO(l.getAddress() + ", " + l.getPlacename(), l.getId()));



            return new AddTaskInfoDTO(empDTOs, locations, jobs);
        }


    }
}
