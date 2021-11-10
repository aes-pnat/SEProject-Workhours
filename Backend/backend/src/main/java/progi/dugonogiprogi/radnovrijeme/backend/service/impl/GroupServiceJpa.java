package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnik;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Djelatnost;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Grupa;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Zadatak;
import progi.dugonogiprogi.radnovrijeme.backend.service.EntityMissingException;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;

import java.util.Optional;
@Service
public class GroupServiceJpa implements GroupService {

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private EmployeeRepository workerRepo;


    @Override
    public void createGroup(String name, Djelatnik leader) {
        Grupa group = new Grupa();
        group.setNaziv(name);
        //group.setLeader(leader);
        groupRepo.save(group);
    }

    @Override
    public void assignJob(Djelatnost job, Long idGroup) {
        Grupa group = this.fetchGroup(idGroup);
        //group.setAssignedJob(job);
    }

    @Override
    public void assignTask(Zadatak task, String idEmployee) {
        Djelatnik worker = this.fetchEmployee(idEmployee);
        //worker.setTask(task);
    }

    @Override
    public void edit(Long idGroup, String idNewEmployee, String idOldEmployee) {
        Djelatnik newWorker = this.fetchEmployee(idNewEmployee);
        Djelatnik oldWorker = this.fetchEmployee(idOldEmployee);
        this.remove(idGroup,oldWorker);
        this.add(idGroup,newWorker);
    }

    @Override
    public void delete(Long idGroup) {
        Grupa group = this.fetchGroup(idGroup);
        //group.setLeader(leader);
        groupRepo.delete(group);
    }

    @Override
    public boolean add(Long idGroup, Djelatnik employee) {
        Djelatnik newWorker = fetchEmployee(employee.getOib());
        if(workerRepo.findByEmployeeId(employee.getOib())!=null) {
            Grupa group = this.fetchGroup(idGroup);
            //group.addEmployee(worker);
            groupRepo.save(group);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Long idGroup, Djelatnik employee) {
        Djelatnik newWorker = fetchEmployee(employee.getOib());
        if(workerRepo.findByEmployeeId(employee.getOib())!=null) {
            Grupa group = this.fetchGroup(idGroup);
            //group.removeEmployee(newWorker);
            groupRepo.save(group);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Grupa fetchGroup(long groupId) {
        return findById(groupId).orElseThrow(
                () -> new EntityMissingException()
        );
    }

    @Override
    public Djelatnik fetchEmployee(String groupId) {
        return findByEmployeeId(groupId).orElseThrow(
                () -> new EntityMissingException()
        );
    }

    @Override
    public Optional<Grupa> findById(long groupId) {
        return groupRepo.findById(groupId);
    }

    @Override
    public Optional<Djelatnik> findByEmployeeId(String idEmployee) {
        return workerRepo.findByEmployeeId(idEmployee);
    }

}