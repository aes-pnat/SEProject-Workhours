package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import org.springframework.scheduling.config.IntervalTask;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class OccupancyDTO {

    private String idEmployee;
    private String dateStart;
    private String dateEnd;

    public OccupancyDTO(String idEmployee, String dateStart, String dateEnd) {
        this.idEmployee = idEmployee;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }
}
