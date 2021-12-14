package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.EmployeeRepository;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Job;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.EntityMissingException;
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
    private GroupRepository groupRepository;

    @Override
    public List<Group> listAllGroups() {

    }

    @Override
    public Group createGroup() {

    }

    @Override
    public void deleteGroup(Integer groupId) {
        if (groupRepository.getById(groupId).getId().equals(groupId)) {
            groupRepository.deleteById(groupId);
        } else {
            throw new MissingGroupException();
        }

    }
}