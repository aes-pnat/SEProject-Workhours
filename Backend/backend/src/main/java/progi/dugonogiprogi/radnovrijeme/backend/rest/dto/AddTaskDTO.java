package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Location;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AddTaskDTO {

    private String taskName;
    private List<String> employeeIDs;
    private String taskDescription;
    private Integer hoursEstimate;
    private Date dateStart;
    private Date dateEnd;
    private Integer jobID;
    private Integer locationID;
    private String newLocationAddress;
    private String newLocationPlaceName;
    private BigDecimal newLocationLatitude;
    private BigDecimal newLocationLongitude;

    public String getTaskName() {
        return taskName;
    }

    public List<String> getEmployeeIDs() {
        return employeeIDs;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Integer getHoursEstimate() {
        return hoursEstimate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Integer getJobID() {
        return jobID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public String getNewLocationAddress() {
        return newLocationAddress;
    }

    public String getNewLocationPlaceName() {
        return newLocationPlaceName;
    }

    public BigDecimal getNewLocationLatitude() {
        return newLocationLatitude;
    }

    public BigDecimal getNewLocationLongitude() {
        return newLocationLongitude;
    }
}
