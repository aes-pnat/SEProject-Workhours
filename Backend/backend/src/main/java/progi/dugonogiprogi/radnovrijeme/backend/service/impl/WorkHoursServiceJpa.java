package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeetaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.WorkHoursRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchTaskException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.WorkHoursService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (hoursDone < 0 || hoursDone > 24)
            throw new IllegalArgumentException("Number of hours done should be between 0 and 24.");
        if (!employeeRepository.findById(idEmployee).isPresent())
            throw new MissingEmployeeException("Employee with ID >" + "< doesn't exist.");
        Task task = null;
        for (Task t : taskRepository.findAll()) {
            if (t.getName().equals(taskName)) {
                task = t;
            }
        }
        if (task == null)
            throw new NoSuchTaskException("Task with the name " + taskName + " doesn't exist.");
        Employee employee = employeeRepository.findById(idEmployee).get();
        Workhoursinput workhoursinput = new Workhoursinput(task, date, hoursDone, employee);
        workHoursRepository.save(workhoursinput);
        return workhoursinput;
    }

    @Override
    public List<String> listTaskNamesForEmployee(String idEmployee) {
        if (idEmployee == null || idEmployee.isEmpty())
            throw new IllegalArgumentException("ID of the employee should be defined.");
        if (!employeeRepository.findById(idEmployee).isPresent())
            throw new MissingEmployeeException("Employee with ID >" + idEmployee + "< doesn't exist.");
        Optional<List<Employeetask>> employeeTaskList = employeetaskRepository.findById_Idemployee(idEmployee);
        if (!employeeTaskList.isPresent())
            return new ArrayList<>();
        List<Integer> taskIDList = new ArrayList<>();
        for (Employeetask et : employeeTaskList.get())
            taskIDList.add(et.getId().getIdtask());
        List<String> taskNames = new ArrayList<>();
        for (Task task : taskRepository.findAll())
            if (taskIDList.contains(task.getId()))
                if (task.getDatetimestart().compareTo(Instant.now()) <= 0 && task.getDatetimeend().compareTo(Instant.now()) >= 0)
                    taskNames.add(task.getName());
        return taskNames;
    }

}
