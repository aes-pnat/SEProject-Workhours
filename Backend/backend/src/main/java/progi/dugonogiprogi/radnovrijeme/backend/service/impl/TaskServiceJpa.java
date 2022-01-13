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
    public List<TasksDTO> listTasksForLeader(String idLeader) {
        List<TasksDTO> list = new LinkedList<>();

        Optional<Employee> lead = employeeRepository.findById(idLeader);

        if (!lead.isPresent())
            throw new MissingEmployeeException("Employee with id " + idLeader + " not found");

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
            if (!locationRepository.findById(locID).isPresent())
                throw new IllegalArgumentException("Location with ID " + locID + " doesn't exist!");
            loc = locationRepository.findById(locID).get();
        }
        Integer jobID = addTaskDTO.getJobID();
        if (!jobRepository.findById(addTaskDTO.getJobID()).isPresent())
            throw new IllegalArgumentException("Job with ID " + jobID + " doesn't exist!");
        Job job = jobRepository.findById(addTaskDTO.getJobID()).get();
        Task task = new Task(addTaskDTO.getTaskName(), addTaskDTO.getTaskDescription(), addTaskDTO.getDateStart().toInstant(), addTaskDTO.getDateEnd().toInstant(), addTaskDTO.getHoursEstimate(), job, loc);
        taskRepository.save(task);
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
