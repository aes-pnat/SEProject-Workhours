package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeetaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.WorkHoursRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.EntityMissingException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchTaskException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.WorkHoursService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WorkHoursServiceJpa implements WorkHoursService {

    @Autowired
    WorkHoursRepository workHoursRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeetaskRepository employeetaskRepository;

    @Override
    public Workhoursinput createNewWorkHoursInput(String taskName, LocalDate date, Integer hoursDone, String idEmployee) {
        String user = BackendApplication.getUser();
        if (hoursDone < 0 || hoursDone > 24) {
            log.error("{}: Creating work hours input failed: Number of hours {} should be between 0 and 24", user, hoursDone);
            throw new IllegalArgumentException("Number of hours done should be between 0 and 24.");
        }
        if (employeeRepository.findById(idEmployee).isEmpty()) {
            log.error("{}: Creating work hours input failed: Employee with inputted id doesn't exist", user);
            throw new MissingEmployeeException("Employee with id " + idEmployee + " doesn't exist.");
        }

        Task task = null;
        for (Task t : taskRepository.findAll()) {
            if (t.getName().equals(taskName)) {
                task = t;
            }
        }
        if (task == null) {
            log.error("{}: Creating work hours input failed: Task with the name {} doesn't exist", user, taskName);
            throw new NoSuchTaskException("Task with the name " + taskName + " doesn't exist.");
        }

        Employee employee = employeeRepository.findById(idEmployee).get();
        Workhoursinput workhoursinput = new Workhoursinput(task, date, hoursDone, employee);
        workHoursRepository.save(workhoursinput);
        log.info("{}: Creating work hours input successful: Created new work hours input for employee {} at date {}", user, employee.getUsername(), date.toString());
        return workhoursinput;
    }

    @Override
    public List<String> listTaskNamesForEmployee() {
        String user = BackendApplication.getUser();

        Optional<Employee> optionalEmployee = employeeRepository.findByUsername(user);
        if(optionalEmployee.isEmpty()) {
            log.error("{}: Listing tasks for workhoursinput failed: Employee with username {} does not exist", user, user);
            throw new EntityMissingException("Employee with username " + user + " does not exist.");
        }
        Employee employee = optionalEmployee.get();

        List<Employeetask> employeeTaskList = employeetaskRepository.findById_Idemployee(employee.getId());
        if (employeeTaskList.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> taskIDList = new ArrayList<>();
        for (Employeetask et : employeeTaskList) {
            taskIDList.add(et.getId().getIdtask());
        }

        List<String> taskNames = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            if (taskIDList.contains(task.getId())) {
                if (task.getDatetimestart().compareTo(Instant.now()) <= 0
                        && task.getDatetimeend().compareTo(Instant.now()) >= 0) {
                    taskNames.add(task.getName());
                }
            }
        }
        return taskNames;
    }

}
