package progi.dugonogiprogi.radnovrijeme.backend.service;


import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.List;
import java.util.Optional;

/**
 * Specifies how certain business functionalities should be provided
 *
 */

@Service
public interface GroupService {

    /**
     * Creates a new group with given name and a leader
     *
     * @param name String you want to use as a group name
     * @param leader Employee set as leader of a group
     */

    void createGroup(String name, Employee leader);

    void assignJob(Job job, Long idGroup);

    /**
     * Assigns task to an employee of a group
     *
     * @param task Task given to an employee
     * @param idEmployee Long value of an employee identification number
     */

    void assignTask(Task task, String idEmployee);

    /**
     * Edits selected group parameters
     *
     * @param idGroup Long value of group identification number
     * @param idNewEmployee Long value of an identification number that belongs to replaced employee
     * @param idOldEmployee Long value of an identification number that belongs to an added employee
     */

    void edit(Long idGroup, String idNewEmployee, String idOldEmployee);

    /**
     * Deletes selected group
     *
     * @param idGroup Long value of group identification number
     */

    void delete(Long idGroup);

    /**
     * Adds selected employee to selected group
     *
     * @param idGroup Long value of group identification number
     * @param worker Employee being added to a group
     * @return true if selected employee isn't already in a selected group, false otherwise
     */

    boolean add(Long idGroup, Employee worker);

    /**
     * Removes selected employee from a selected group
     *
     * @param idGroup Long value of group identification number
     * @param worker Employee being removed from group
     * @return true if selected employee is in a selected group, false otherwise
     */

    boolean remove(Long idGroup, Employee worker);

    /**
     * Passes given id of a group to a method that uses group repository to find requested Group
     *
     * @param groupId Long value of group identification number
     * @return Group with given identification number
     */

    Group fetchGroup(long groupId);

    /**
     * Passes given id of an employee to a method that uses employee        repository to find requested Employee
     *
     * @param idEmployee Long value of an employee identification number
     * @return Employee with given identification number
     */

    Employee fetchEmployee(String idEmployee);

    /**
     * Searches for a group with given id in a group repository
     *
     * @param groupId Long value of group identification number
     * @return Group with given identification number
     */

    Optional<Group> findById(long groupId);

    /**
     * Searches for an Employee with given id in an employee repository
     *
     * @param idEmployee Long value of an employee identification number
     * @return Employee with given identification number
     */

    Optional<Employee> findByEmployeeId(String idEmployee);

    /**
     * Returns selected group
     *
     * @param idGroup Long value of group identification number
     * @return selected Group
     */
    Group returnGroup(Long idGroup);

    /**
     * Returns a list of all existing groups
     *
     * @return List of all groups from a group repository
     */
    List<Group> returnAllGroups();
}