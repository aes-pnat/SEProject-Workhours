package progi.dugonogiprogi.radnovrijeme.backend.rest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;

import java.util.List;
//import org.springframework.security.access.annotation.Secured;

/**
 *  Handles requests for the application Group page.
 */
@RestController
@RequestMapping("/groups")
public class GroupController {


    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    public GroupService groupService;

    /**
     * Handles HTTP request for group creation.
     *
     * @param name String you want to use as a group name
     * @param leader Employee set as leader of a group
     */
    @Secured("ROLE_OWNER")
    @PutMapping("/create")
    public void createGroup(@RequestBody String name, Employee leader) {
        groupService.createGroup(name, leader);
    }

    /**
     * Handles HTTP request for assigning a job to a selected group.
     *
     * @param job Job that a group will be doing
     * @param idGroup Long value of group identification number
     */

    @Secured("ROLE_OWNER")
    @PutMapping("/{idGroup}/assignJob")
    public void assignJob(@RequestBody Job job, @PathVariable("idGroup") Long idGroup) {
        groupService.assignJob(job, idGroup);
    }

    /**
     * Handles HTTP request for assigning a task to selected employee.
     *
     * @param task Task given to an employee
     * @param idEmployee Long value of an employee identification number
     */
    @Secured("ROLE_LEADER")
    @PutMapping("/{idEmployee}/assignTask")
    public void assignTask(Task task, @PathVariable("idEmployee") String idEmployee) {
        groupService.assignTask(task, idEmployee);
    }

    /**
     * Handles HTTP request for editing selected group settings.
     *
     * @param idGroup Long value of group identification number
     * @param idNewEmployee Long value of an identification number that belongs to replaced employee
     * @param idOldEmployee Long value of an identification number that belongs to an added employee
     */
    @Secured("ROLE_OWNER")
    @PatchMapping("/{idGroup}/edit")
    public void edit(@PathVariable("idGroup") Long idGroup, String idNewEmployee, String idOldEmployee){
        groupService.edit(idGroup, idNewEmployee, idOldEmployee);
    }

    /**
     * Handles HTTP request for deleting a selected group.
     *
     * @param idGroup Long value of group identification number
     */
    @Secured("ROLE_OWNER")
    @DeleteMapping("/{idGroup}/delete")
    public void delete(@PathVariable("idGroup") Long idGroup) {
        groupService.delete(idGroup);
    }

    /**
     * Handles HTTP request for adding new employee to a selected group.
     *
     * @param idGroup Long value of group identification number
     * @param worker Employee being added to a group
     * @return true if selected employee isn't already in a selected group, false otherwise
     */
    @Secured("ROLE_OWNER")
    @PutMapping("/{idGroup}/add")
    public boolean add(@PathVariable("idGroup") Long idGroup, @RequestBody Employee worker){
        return groupService.add(idGroup,worker);
    }

    /**
     * Handles HTTP request for removing selected employee from a selected group.
     *
     * @param idGroup Long value of group identification number
     * @param worker Employee being removed from group
     * @return true if selected employee is in a selected group, false otherwise
     */
    @Secured("ROLE_LEADER")
    @DeleteMapping("/{idGroup}/remove")
    public boolean remove(@PathVariable("idGroup") Long idGroup, @RequestBody Employee worker){
        return groupService.remove(idGroup,worker);
    }

    /**
     * Handles HTTP request to view details of a selected group.
     *
     * @param idGroup Long value of group identification number
     * @return selected Group
     */
    @Secured("ROLE_USER")
    @GetMapping("/{idGroup}")
    public Group returnGroup(@PathVariable("idGroup") Long idGroup) {
        return groupService.returnGroup(idGroup);
    }

    /**
     * Handles HTTP request to view all groups.
     *
     * @return List of all groups from a group repository
     */
    @Secured("ROLE_OWNER")
    @GetMapping("")
    public List<Group> returnAllGroups() {
        return groupService.returnAllGroups();
    }

}