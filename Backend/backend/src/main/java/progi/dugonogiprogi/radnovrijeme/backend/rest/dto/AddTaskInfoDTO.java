package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.EmployeeDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.JobDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.LocationDTO;

import java.util.List;

public class AddTaskInfoDTO {

    private List<Group> groups;
    private List<EmployeeDTO> employees;
    private List<LocationDTO> existingLocations;
    private List<JobDTO> jobs;

    public AddTaskInfoDTO() {

    }

    public AddTaskInfoDTO(List<EmployeeDTO> employees, List<LocationDTO> existingLocations, List<JobDTO> jobs) {
        this.employees = employees;
        this.existingLocations = existingLocations;
        this.jobs = jobs;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public List<LocationDTO> getExistingLocations() {
        return existingLocations;
    }

    public List<JobDTO> getJobs() {
        return jobs;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public void setExistingLocations(List<LocationDTO> existingLocations) {
        this.existingLocations = existingLocations;
    }

    public void setJobs(List<JobDTO> jobs) {
        this.jobs = jobs;
    }
}