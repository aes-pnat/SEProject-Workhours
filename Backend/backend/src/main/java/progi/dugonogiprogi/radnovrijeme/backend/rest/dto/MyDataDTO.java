package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.List;
import java.util.Map;

/**
 *This data transfer object contains data about employees
 *
 */
public class MyDataDTO {

    /**
     * Employee's name
     */
    private String name;
    /**
     * Employee's surname
     */
    private String surname;
    /**
     * Employee's e-mail
     */
    private String email;
    /**
     * Employee's username
     */
    private String username;
    /**
     * Employee's pid
     */
    private String pid;
    /**
     * Name of employee's role
     */
    private String roleName;
    /**
     * List of names of groups employee is in
     */
    private List<String> groupNames;
    /**
     * List of tasks given to employee
     */
    private List<GroupTaskDTO> tasks;

    /**
     * Getter for employee's name
     * @return String value of employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets employee's name
     *
     * @param name String value to which employee's name is set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for employee's surname
     * @return String value of employee's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets employee's surname
     *
     * @param surname String value to which employee's surname is set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for employee's email
     * @return String value of employee's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets employee's email
     *
     * @param email String value to which employee's email is set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for employee's username
     * @return String value of employee's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets employee's username
     *
     * @param username String value to which employee's username is set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for employee's pid
     * @return String value of employee's pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * Sets employee's pid
     *
     * @param pid String value to which employee's username is set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * Getter for the name of employee's role
     * @return String value of the name of employee's role
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets name of employee's role
     *
     * @param roleName String value of employee's role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Getter for list of names of groups employee is in
     * @return List containing String values of names of groups employee is in
     */
    public List<String> getGroupNames() {
        return groupNames;
    }

    /**
     * Sets list of names of groups employee is in
     *
     * @param groupNames list of names of groups employee is in
     */
    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    /**
     * Getter for list of tasks given to employee
     *
     * @return List containing Tasks given to employee
     */

    public List<GroupTaskDTO> getTasks() {
        return tasks;
    }

    /**
     * Sets list of tasks given to employee
     *
     * @param tasks List containing Tasks given to employee
     */
    public void setTasks(List<GroupTaskDTO> tasks) {
        this.tasks = tasks;
    }
}
