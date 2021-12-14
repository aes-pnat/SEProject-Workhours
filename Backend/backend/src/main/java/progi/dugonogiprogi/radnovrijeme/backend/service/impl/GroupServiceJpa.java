package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.exception.MissingGroupException;
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
        return groupRepository.findAll();
    }

    @Override
    public Group createGroup(GroupDTO group) {
        Group newGroup = new Group();
        newGroup.setName(group.getName());
        newGroup.setIdleader(group.getLeader());
        newGroup.setIdjob(group.getIdJob());
        return newGroup;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        if (groupRepository.getById(groupId).getId().equals(groupId)) {
            groupRepository.deleteById(groupId);
        } else {
            throw new MissingGroupException("Group with given groupID not found.");
        }

    }
}