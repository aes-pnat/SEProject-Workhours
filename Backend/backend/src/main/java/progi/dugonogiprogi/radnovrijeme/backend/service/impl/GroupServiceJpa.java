package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.BackendApplication;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddGroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingGroupException;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.GroupService;

import java.util.*;

/**
 * Provides some business functionalities.
 *
 */

@Slf4j
@Service
public class GroupServiceJpa implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmployeegroupRepository employeegroupRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<GroupDTO> listAllGroups() {
        List<GroupDTO> listGroups = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for (Group g : groups) {
            GroupDTO group = new GroupDTO();
            group.setId(g.getId());
            group.setName(g.getName());
            group.setLeader(g.getIdleader());
            group.setJob(g.getIdjob());

            List<Employeegroup> employeegroups = employeegroupRepository.findById_Idgroup(g.getId());
            List<Employee> employeeList = new ArrayList<>();
            for (Employeegroup e : employeegroups) {
                Employee employee = employeeRepository.findById(e.getId().getIdemployee()).get();
                employeeList.add(employee);
            }
            group.setMembers(employeeList);

            listGroups.add(group);
        }
        return listGroups;
    }

    @Override
    public Group createGroup(AddGroupDTO group) {
        String user = BackendApplication.getUser();
        Group newGroup = new Group();

        if (groupRepository.findByName(group.getGroupName()).isPresent()) {
            log.error("{}: Creating group failed: Group with name {} already exists", user, group.getGroupName());
            throw new IllegalArgumentException("Group with name " + group.getGroupName() + " already exists");
        }

        newGroup.setName(group.getGroupName());

        Optional<Job> job = jobRepository.findById(group.getIdJob());
        newGroup.setIdjob(job.get());

        Optional<Employee> leader = employeeRepository.findById(group.getIdLeader());
        if(leader.isEmpty()) {
            log.error("{}: Creating group failed: Employee with pid {} does not exist", user, group.getIdLeader());
            throw new IllegalArgumentException("Employee with pid " + group.getIdLeader() + " does not exist");
        }
        if(leader.get().getIdrole().getName().equals("employee")) {
            Optional<Role> role = roleRepository.findByName("leader");
            if(role.isPresent()) {
                leader.get().setIdrole(role.get());
                employeeRepository.save(leader.get());
                log.info("{}: Creating group info: Employee with pid {} had been promoted to leader", user, leader.get().getId());
            }
        }

        newGroup.setIdleader(leader.get());
        groupRepository.save(newGroup);



        List<Employee> members = new ArrayList<>();
        for(String idMember : group.getIdMembers()) {
            Optional<Employee> e = employeeRepository.findById(idMember);
            if(e.isEmpty()) {
                log.error("{}: Creating group failed: Employee with pid {} does not exist", user, idMember);
                throw new IllegalArgumentException("Employee with pid " + idMember + " does not exist");
            }

            members.add(e.get());
        }

        for(Employee e : members) {
            EmployeegroupId employeegroupId = new EmployeegroupId();
            employeegroupId.setIdemployee(e.getId());
            employeegroupId.setIdgroup(groupRepository.findByName(group.getGroupName()).get().getId());
            Employeegroup employeegroup = new Employeegroup();
            employeegroup.setId(employeegroupId);
            employeegroupRepository.save(employeegroup);
        }

        log.info("{}: Creating group successful: Created group with id {}", user, newGroup.getId());

        return groupRepository.findByName(newGroup.getName()).get();
    }

    @Override
    public Integer deleteGroup(Integer groupId) {
        String user = BackendApplication.getUser();
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isEmpty()) {
            log.error("{}: Deleting group failed: Group with id {} does not exist", user, groupId);
            throw new MissingGroupException("Group with id " + groupId + " does not exist");
        }

        log.info("{}: Deleting group successful: Deleted group with id {}", user, groupId);
        groupRepository.deleteById(groupId);

        List<Group> leadingGroups = groupRepository.findByIdleader_Id(group.get().getIdleader().getId());
        if(leadingGroups.isEmpty()) {
            Optional<Role> role = roleRepository.findByName("employee");
            if(role.isPresent()) {
                Employee exLeader = group.get().getIdleader();
                exLeader.setIdrole(role.get());
                employeeRepository.save(exLeader);
                log.info("{}: Deleting group info: Employee with pid {} had been demoted to employee", user, exLeader.getId());
            }
        }
        return groupId;
    }
}