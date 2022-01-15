package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Workhoursinput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.WorkHoursInputDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Specifies how will work hours site process data and
 * what functionalities this service will provide.
 */
@Service
public interface WorkHoursService {

    List<String> listTaskNamesForEmployee();

    Workhoursinput createNewWorkHoursInput(String taskName, LocalDate date, Integer hoursDone, String idEmployee);

}
