package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.service.EntityMissingException;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Provides some business functionalities.
 *
 */

@Service
public class GroupServiceJpa implements GroupService {

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private EmployeeRepository workerRepo;


    @Override
    public void createGroup(String name, Employee leader) {
        Group group = new Group();
        group.setName(name);
        group.setLeader(leader);
        groupRepo.save(group);
    }

    @Override
    public void assignJob(Job job, Long idGroup) {
        Group group = this.fetchGroup(idGroup);
        group.setAssignedJob(job);
    }

    @Override
    public void assignTask(Task task, String idEmployee) {
        Employee worker = this.fetchEmployee(idEmployee);
        Set<Task> tasks = worker.getTasks();
        tasks.add(task);
        worker.setTasks(tasks);
    }

    @Override
    public void edit(Long idGroup, String idNewEmployee, String idOldEmployee) {
        Employee newWorker = this.fetchEmployee(idNewEmployee);
        Employee oldWorker = this.fetchEmployee(idOldEmployee);
        this.remove(idGroup,oldWorker);
        this.add(idGroup,newWorker);
    }

    @Override
    public void delete(Long idGroup) {
        Group group = this.fetchGroup(idGroup);
        groupRepo.delete(group);
    }

    @Override
    public boolean add(Long idGroup, Employee employee) {
        Employee newWorker = fetchEmployee(employee.getPid());
        if(workerRepo.findByEmployeeId(employee.getPid())!=null) {
            Group group = this.fetchGroup(idGroup);
            Set<Employee> workers = group.getMembers();
            workers.add(newWorker);
            group.setMembers(workers);
            groupRepo.save(group);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long idGroup, Employee employee) {
        Employee newWorker = fetchEmployee(employee.getPid());
        if(workerRepo.findByEmployeeId(employee.getPid())!=null) {
            Group group = this.fetchGroup(idGroup);
            Set<Employee> workers = group.getMembers();
            workers.remove(newWorker);
            group.setMembers(workers);
            groupRepo.save(group);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Group fetchGroup(long groupId) {
        return findById(groupId).orElseThrow(
                () -> new EntityMissingException()
        );
    }

    @Override
    public Employee fetchEmployee(String groupId) {
        return findByEmployeeId(groupId).orElseThrow(
                () -> new EntityMissingException()
        );
    }

    @Override
    public Optional<Group> findById(long groupId) {
        return groupRepo.findById(groupId);
    }

    @Override
    public Optional<Employee> findByEmployeeId(String idEmployee) {
        return workerRepo.findByEmployeeId(idEmployee);
    }

    @Override
    public Group returnGroup(Long idGroup) {
        return fetchGroup(idGroup);
    }

    @Override
    public List<Group> returnAllGroups() {
        return groupRepo.findAll();
    }

}