package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Location;

import java.time.Instant;

public class LocationDataDTO {

    private Location location;

    private String employeeName;

    private String employeeSurname;

    private Instant startDateAndTime;

    private Instant endDateAndTime;

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public Location getLocation(){
        return location;
    }

    public String getEmployeeName(){
        return employeeName;
    }

    public Instant getStartDateAndTime(){
        return startDateAndTime;
    }

    public Instant getEndDateAndTime(){
        return endDateAndTime;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setEndDateAndTime(Instant endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    public void setStartDateAndTime(Instant startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

}
