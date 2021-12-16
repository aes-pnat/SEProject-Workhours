package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeetaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.LocationRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.TaskRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDataDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.MapService;

import java.util.LinkedList;
import java.util.List;

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
        LocationDataDTO dataDTO = new LocationDataDTO();
        for(Location location : locationRepository.findAll()) {
            dataDTO.setLocation(location);
            Integer id = location.getId();
            for(Task task : taskRepository.findAll()) {
                if(task.getIdlocation().getId().equals(id)){
                    dataDTO.setStartDateAndTime(task.getDatetimestart());
                    dataDTO.setEndDateAndTime(task.getDatetimeend());
                    for(Employeetask et : employeetaskRepository.findAll()){
                            if(et.getId().getIdtask().equals(task.getId())){
                                    String idEmployee = et.getId().getIdemployee();
                                    for(Employee employee : employeeRepository.findAll()) {
                                        if(employee.getId().equals(idEmployee)){
                                            dataDTO.setEmployeeName(employee.getName());
                                            dataDTO.setEmployeeSurname(employee.getSurname());
                                        }
                                    }
                            }
                    }
                }
            }
        }
        return list;
    }

}
