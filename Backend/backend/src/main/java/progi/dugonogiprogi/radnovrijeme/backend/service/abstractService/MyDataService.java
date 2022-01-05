package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MyDataDTO;


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
