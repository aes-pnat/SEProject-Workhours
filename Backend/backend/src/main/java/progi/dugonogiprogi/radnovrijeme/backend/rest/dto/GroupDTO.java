package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class GroupDTO {

    @NotEmpty(message = "ID must not be null")
    Integer id;

    @NotEmpty(message = "Name should not be empty.")
    String name;

    @NotEmpty(message = "Group leader should be selected.")
    Employee leader;

    Job idJob;

    @NotEmpty(message = "At least one employee should be selected.")
    List<Employee> members;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getLeader() {
        return leader;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }

    public Job getIdJob() {
        return idJob;
    }

    public void setIdJob(Job idJob) {
        this.idJob = idJob;
    }

    public List<Employee> getMembers() {
        return members;
    }

    public void setMembers(List<Employee> members) {
        this.members = members;
    }
}
