package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Location;
import java.time.Instant;

/**
 * Data transfer object that stores all tasks from a group of one employee.
 */
public class TasksDTO {


    private String employeeName;

    private String employeeSurname;

    private Location location;

    private Job job;

    private Integer estimatedDuration;

    private String taskName;

    private Instant startDateAndTime;

    private Instant endDateAndTime;


    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Instant getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(Instant startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public Instant getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime(Instant endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }
}
