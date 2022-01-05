package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeetaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeetask;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.EmployeeDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.TimePeriodException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.OccupancyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OccupancyServiceJpa implements OccupancyService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeetaskRepository employeetaskRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<EmployeeDTO> listAllEmployees() {
        List<EmployeeDTO> list = new ArrayList<>();
        EmployeeDTO dto;
        for (Employee emp : employeeRepository.findAll()) {
            dto = new EmployeeDTO(emp.getName() + " " + emp.getSurname(), emp.getId());
            list.add(dto);
        }
        return list;
    }



    private static boolean checkOccupancy(Task task, Date dateStart, Date dateEnd) {
        return Date.from(task.getDatetimestart()).compareTo(dateStart) <= 0 && Date.from(task.getDatetimeend()).compareTo(dateEnd) >= 0
                || Date.from(task.getDatetimestart()).compareTo(dateStart) <= 0 && Date.from(task.getDatetimeend()).compareTo(dateStart) >= 0 && Date.from(task.getDatetimeend()).compareTo(dateEnd) <= 0
                || Date.from(task.getDatetimestart()).compareTo(dateStart) >= 0 && Date.from(task.getDatetimestart()).compareTo(dateEnd) <= 0  && Date.from(task.getDatetimeend()).compareTo(dateEnd) >= 0
                || Date.from(task.getDatetimestart()).compareTo(dateStart) >= 0 && Date.from(task.getDatetimeend()).compareTo(dateEnd) <= 0;
    }

    @Override
    public String isOccupied(String id, Date dateStart, Date dateEnd) {
        if (!employeeRepository.findById(id).isPresent())
            throw new MissingEmployeeException("Employee with ID >" + id + "< doesn't exist.");
        if (dateStart.after(dateEnd))
            throw new TimePeriodException("The ending date cannot be a date before the starting date.");
        Optional<List<Employeetask>> employeeTaskList = employeetaskRepository.findById_Idemployee(id);
        if (!employeeTaskList.isPresent())
            return "Djelatnik je slobodan u odabranom periodu.";
        List<Integer> taskIDList = new ArrayList<>();
        for (Employeetask et : employeeTaskList.get())
            taskIDList.add(et.getId().getIdtask());
        boolean occupied = false;
        for (Task task : taskRepository.findAll())
            if (taskIDList.contains(task.getId()))
                if (checkOccupancy(task, dateStart, dateEnd))
                    occupied = true;
        return occupied ? "Djelatnik je zauzet u odabranom periodu." : "Djelatnik je slobodan u odabranom periodu.";

    }

}
