package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;

import java.util.List;

/**
 * Specifies how will mydata site process data and
 * what functionalities this service will provide.
 */
@Service
public interface MyDataService {


    /**
     * Returns DTO of information about an employee
     *
     * @param username employee's username given as a requested parameter
     * @return DTO of information about an employee whose username is @param username
     */
    MyDataDTO myData(String username);
}
