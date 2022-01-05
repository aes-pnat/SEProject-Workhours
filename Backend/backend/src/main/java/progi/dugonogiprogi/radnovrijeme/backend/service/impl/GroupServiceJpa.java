package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeegroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeegroupId;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
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

    @Override
    public List<GroupDTO> listAllGroups() {
        List<GroupDTO> listGroups = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        for (Group g : groups) {
            GroupDTO group = new GroupDTO();
            group.setId(g.getId());
            group.setName(g.getName());
            group.setLeader(g.getIdleader());
            group.setIdJob(g.getIdjob());


            List<Employeegroup> employeegroups = employeegroupRepository.findById_Idgroup(g.getId()).get();
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
        Group newGroup = new Group();

        if (groupRepository.findByName(group.getGroupName()).isPresent()) {
            log.error("Group with name {} already exists", group.getGroupName());
            throw new IllegalArgumentException("Group with name " + group.getGroupName() + " already exists");
        }

        newGroup.setName(group.getGroupName());

        Optional<Employee> leader = employeeRepository.findById(group.getIdLeader());
        if(leader.isEmpty()) {
            log.error("Employee with pid {} does not exist", group.getIdLeader());
            throw new IllegalArgumentException("Employee with pid " + group.getIdLeader() + " does not exist");
        }

        newGroup.setIdleader(leader.get());
        groupRepository.save(newGroup);

        List<Employee> members = new ArrayList<>();
        for(String idMember : group.getIdMembers()) {
            Optional<Employee> e = employeeRepository.findById(idMember);
            if(e.isEmpty()) {
                log.error("Employee with pid {} does not exist", idMember);
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

        return groupRepository.findByName(newGroup.getName()).get();
    }

    @Override
    public Integer deleteGroup(Integer groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isEmpty()) {
            log.error("Group with id {} does not exist", groupId);
            throw new MissingGroupException("Group with id " + groupId + " does not exist");
        }

        groupRepository.deleteById(groupId);
        return groupId;
    }
}