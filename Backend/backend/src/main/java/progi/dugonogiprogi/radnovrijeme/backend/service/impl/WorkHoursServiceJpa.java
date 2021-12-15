package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.WorkHoursRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Role;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.WorkHoursInputDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.NoSuchTaskException;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkHoursServiceJpa implements WorkHoursService {

    @Autowired
    WorkHoursRepository workHoursRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Workhoursinput createNewWorkHoursInput(String taskName, LocalDate date, Integer hoursDone, Integer idEmployee) {
        Task task = null;
        for (Task t : taskRepository.findAll()) {
            if (t.getName().equals(taskName)) {
                task = t;
            }
        }
        if (task == null)
            throw new NoSuchTaskException(taskName);
        Employee employee = null;
        for (Employee e : employeeRepository.findAll()) {
            if (e.getId().equals(idEmployee)) {
                employee = e;
            }
        }
        if (employee == null)
            throw new MissingEmployeeException("Employee with ID >" + "< doesn't exist.");
        Workhoursinput workhoursinput = new Workhoursinput(task, date, hoursDone, employee);
        workHoursRepository.save(workhoursinput);
        return workhoursinput;
    }

    @Override
    public List<Workhoursinput> listAllWorkHours() {
        return workHoursRepository.findAll();
    }

}
