package progi.dugonogiprogi.radnovrijeme.backend.service;


import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;

import java.util.List;
import java.util.Optional;

/**
 * Specifies how certain business functionalities should be provided.
 */
@Service
public interface GroupService {

    public List<Group> listAllGroups();

    public Group createGroup();

    public void deleteGroup(Integer groupId);

}