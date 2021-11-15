package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;
import progi.dugonogiprogi.radnovrijeme.backend.rest.WorkHoursInputDTO;

import java.util.List;

/**
 * Specifies how will work hours site process data and
 * what functionalities this service will provide.
 */
@Service
public interface WorkHoursService {

    /**
     * This method gets data from repository.
     * @return list of all work hours inputs for a given employee.
     */
    List<WorkHoursInput> listWorkHoursEmployee(String username);

    /**
     * This method gets data from repository and processes it into a data transfer object.
     * @return A data transfer object that contains all work hours inputs and
     * a sum of work hours done for each worker.
     */
    List<WorkHoursInputDTO> listAllWorkHours();

    /**
     * This method gets data from repository and processes it into a data transfer object.
     * @param pid Personal identification number of an employee.
     * @return A data transfer object that contains all work hours inputs and
     * a sum of work hours done for a worker with given pid.
     */
    WorkHoursInputDTO workHoursEmployee(String pid);

    /**
     * This method receives work hours input and stores it in database.
     * @param workHoursInput work hours input that needs to be stored.
     */
    void inputWorkHours(WorkHoursInput workHoursInput);

}
