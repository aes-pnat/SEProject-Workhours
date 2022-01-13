package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OccupancyDTO {

    @NotEmpty(message = "ID of the employee should not be empty.")
    private String id;
    @NotNull(message = "Starting date of the time period should be selected.")
    private String dateStart;
    @NotNull(message = "Ending date of the time period should be selected.")
    private String dateEnd;

    public OccupancyDTO(String id, String dateStart, String dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getIdEmployee() {
        return id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }
}
