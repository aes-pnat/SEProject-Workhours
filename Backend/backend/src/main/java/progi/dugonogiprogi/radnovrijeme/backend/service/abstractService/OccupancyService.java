package progi.dugonogiprogi.radnovrijeme.backend.service.abstractService;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.EmployeeDTO;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public interface OccupancyService {

    List<EmployeeDTO> listAllEmployees();

    String isOccupied(String id, Date dateStart, Date dateEnd);

}
