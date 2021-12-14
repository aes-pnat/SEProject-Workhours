package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.GroupRepository;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
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

        return null;
    }

    @Override
    public Group createGroup() {

        return null;
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