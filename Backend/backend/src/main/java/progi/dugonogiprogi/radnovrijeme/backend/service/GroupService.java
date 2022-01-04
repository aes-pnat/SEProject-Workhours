package progi.dugonogiprogi.radnovrijeme.backend.service;


import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Employee;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.GroupDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Specifies how certain business functionalities should be provided.
 */
@Service
public interface GroupService {

    public List<GroupDTO> listAllGroups();

    public Group createGroup(GroupDTO group);

    public Integer deleteGroup(Integer groupId);

}