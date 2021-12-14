package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.WorkHoursRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.WorkHoursInputDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.WorkHoursService;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkHoursServiceJpa implements WorkHoursService {

    @Autowired
    WorkHoursRepository workHoursRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Workhoursinput> listWorkHoursEmployee(String username) {
        return workHoursRepository.findByHasDoneEquals(employeeRepository.findByUsername(username).get());
    }

    @Override
    public List<WorkHoursInputDTO> listAllWorkHours() {
        List<Employee> employees = employeeRepository.findAll();
        List<WorkHoursInputDTO> returnList = new ArrayList<>();

        for(Employee e : employees) {
            returnList.add(new WorkHoursInputDTO(e, workHoursRepository.findByHasDoneEquals(e)));
        }

        return returnList;
    }

    @Override
    public WorkHoursInputDTO workHoursEmployee(String pid) {
        Employee employee = employeeRepository.findById(pid).get();
        List<Workhoursinput> workHoursInputList = workHoursRepository.findByHasDoneEquals(employee);

        return new WorkHoursInputDTO(employee, workHoursInputList);
    }

    @Override
    public void inputWorkHours(Workhoursinput workHoursInput) {
        workHoursRepository.save(workHoursInput);
    }
}
