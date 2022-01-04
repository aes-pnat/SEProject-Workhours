package progi.dugonogiprogi.radnovrijeme.backend.service;


import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.AddGroupDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;

import java.util.List;

/**
 * Specifies how certain business functionalities should be provided.
 */
@Service
public interface GroupService {

    List<GroupDTO> listAllGroups();

    Group createGroup(AddGroupDTO group);

    Integer deleteGroup(Integer groupId);

}