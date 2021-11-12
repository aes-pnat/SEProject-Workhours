package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.WorkHoursInputDTO;

import java.util.List;

@Service
public interface WorkHoursService {

    List<WorkHoursInput> listWorkHoursEmployee(String username);

    List<WorkHoursInputDTO> listAllWorkHours();

    WorkHoursInputDTO workHoursEmployee(Long idEmployee);

    void inputWorkHours(WorkHoursInput workHoursInput);

}
