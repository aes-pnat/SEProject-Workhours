package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeetaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.LocationRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingEmployeeException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.MapService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MapServiceJpa implements MapService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeetaskRepository employeetaskRepository;

    @Override
    public List<LocationDataDTO> showLocationData() {
        List<LocationDataDTO> list = new LinkedList<>();
        LocationDataDTO dataDTO;

        for(Location location : locationRepository.findAll()) {
            Integer id = location.getId();
            Optional<List<Task>> tasks = taskRepository.findByIdlocation_Id(id);
            if(tasks.isPresent()){
                for (Task t : tasks.get()) {
                    dataDTO = new LocationDataDTO();
                    dataDTO.setLocation(location);
                    dataDTO.setStartDateAndTime(t.getDatetimestart());
                    dataDTO.setEndDateAndTime(t.getDatetimeend());
                    Optional<List<Employeetask>> employeetask = employeetaskRepository.findById_Idtask(t.getId());
                    if(employeetask.isEmpty()) {
                        throw new MissingEmployeeException("No employees were given task with id " + t.getId());
                    }
                    for(Employeetask et : employeetask.get()){
                        String idEmployee = et.getId().getIdemployee();
                        Optional<Employee> employee = employeeRepository.findById(et.getId().getIdemployee());
                        if(employee.isEmpty()){
                            throw new MissingEmployeeException("Employee with id " + idEmployee + "doesn't exist");
                        }
                        dataDTO.setEmployeeName(employee.get().getName());
                        dataDTO.setEmployeeSurname(employee.get().getSurname());
                    }
                    list.add(dataDTO);
                }
            }
        }
        return list;
    }

}
