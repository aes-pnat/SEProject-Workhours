package progi.dugonogiprogi.radnovrijeme.backend.dao;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Role;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.domain.WorkHoursInput;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class EmployeeDao {

    private String pid;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "idEmployee", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "leader")
    private Set<Group> isLeader;

    @ManyToMany(mappedBy = "members")
    private Set<Group> isMember;

    @ManyToMany
    @JoinTable(
            name = "EmployeeTask",
            joinColumns = @JoinColumn(name = "idEmployee"),
            inverseJoinColumns = @JoinColumn(name = "idTask"))
    private Set<Task> tasks;

    @OneToMany(mappedBy = "hasDone")
    private Set<WorkHoursInput> workHours;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) {	this.password = password; }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Group> getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Set<Group> isLeader) {
        this.isLeader = isLeader;
    }

    public Set<Group> getIsMember() {
        return isMember;
    }

    public void setIsMember(Set<Group> isMember) {
        this.isMember = isMember;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<WorkHoursInput> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Set<WorkHoursInput> workHours) {
        this.workHours = workHours;
    }

}
