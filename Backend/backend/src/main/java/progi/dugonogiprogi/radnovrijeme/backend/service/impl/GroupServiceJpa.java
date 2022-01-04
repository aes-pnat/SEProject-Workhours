package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeegroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employeegroup;
import progi.dugonogiprogi.radnovrijeme.backend.domain.EmployeegroupId;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingGroupException;
import progi.dugonogiprogi.radnovrijeme.backend.service.GroupService;

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

    @Override
    public Map<Group, List<Employee>> listAllGroups() {
        Map<Group, List<Employee>> listGroups = new HashMap<>();
        List<Group> groups = groupRepository.findAll();
        for (Group g : groups) {
          listGroups.put(g, employeegroupRepository.findAllEmployeesByGroupId(g.getId()).get());
        }
        return listGroups;
    }

    @Override
    public Group createGroup(GroupDTO group) {
        Group newGroup = new Group();

        if (groupRepository.findByName(group.getName()).isPresent()) {
            log.error("Group with name {} already exists", group.getName());
            throw new IllegalArgumentException("Group with name " + group.getName() + " already exists");
        }
        newGroup.setName(group.getName());
        newGroup.setIdleader(group.getLeader());

        if (group.getIdJob() != null)  {
            newGroup.setIdjob(group.getIdJob());
        }

        groupRepository.save(newGroup);

        List<Employee> members = group.getMembers();
        for(Employee e : members) {
            EmployeegroupId employeegroupId = new EmployeegroupId();
            employeegroupId.setIdemployee(e.getId());
            employeegroupId.setIdgroup(groupRepository.findByName(group.getName()).get().getId());
            Employeegroup employeegroup = new Employeegroup();
            employeegroup.setId(employeegroupId);
            employeegroupRepository.save(employeegroup);
        }

        return groupRepository.findByName(newGroup.getName()).get();
    }

    @Override
    public Integer deleteGroup(Integer groupId) {
        if (groupRepository.getById(groupId).getId().equals(groupId)) {
            groupRepository.deleteById(groupId);
            return groupId;
        } else {
            throw new MissingGroupException("Group with given groupID not found.");
        }

    }
}