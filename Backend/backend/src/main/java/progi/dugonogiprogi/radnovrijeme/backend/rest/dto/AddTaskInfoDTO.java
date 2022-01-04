package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import java.util.List;

public class AddTaskInfoDTO {

    private List<EmployeeDTO> employees;
    private List<LocationDTO> existingLocations;
    private List<JobDTO> jobs;

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
}
