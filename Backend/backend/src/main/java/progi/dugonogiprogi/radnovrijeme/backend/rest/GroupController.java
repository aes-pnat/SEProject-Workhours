package progi.dugonogiprogi.radnovrijeme.backend.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;
//import org.springframework.security.access.annotation.Secured;


@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    public GroupService groupService;

    //@Secured("OWNER")
    @PutMapping("/create")
    public void createGroup(@RequestBody String name, Employee leader) {
        groupService.createGroup(name, leader);
    }

    @PutMapping("/{idGroup}/assignJob")
    public void assignJob(@RequestBody Job job, @PathVariable("idGroup") Long idGroup) {
        groupService.assignJob(job, idGroup);
    }

    @PutMapping("/{idGroup}/{idEmployee}/assignTask")
    public void assignTask(Task task, @PathVariable("idGroup") String idEmployee) {
        groupService.assignTask(task, idEmployee);
    }

    @PatchMapping("/{idGroup}/edit")
    public void edit(@PathVariable("idGroup") Long idGroup, String idNewEmployee, String idOldEmploee){
        groupService.edit(idGroup, idNewEmployee, idOldEmploee);
    }

    @DeleteMapping("/{idGroup}/delete")
    public void delete(@PathVariable("idGroup") Long idGroup) {
        groupService.delete(idGroup);
    }

    @PutMapping("/{idGroup}/add")
    public boolean add(@PathVariable("idGroup") Long idGroup, @RequestBody Employee worker){
        return groupService.add(idGroup,worker);
    }

    @DeleteMapping("/{idGroup}/remove")
    public boolean remove(@PathVariable("idGroup") Long idGroup, @RequestBody Employee worker){
        return groupService.remove(idGroup,worker);
    }

}