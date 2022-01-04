package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class AddGroupDTO {

    @NotEmpty(message = "Group name is required.")
    String groupName;

    @NotEmpty(message = "Group leader is required.")
    @Size(min = 11, max = 11, message = "Leader pid should be 11 characters long.")
    String idLeader;

    @NotEmpty(message = "At least one member is required.")
    @Size(min = 11, max = 11, message = "Member pid should be 11 characters long.")
    List<String> idMembers;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIdLeader() {
        return idLeader;
    }

    public void setIdLeader(String idLeader) {
        this.idLeader = idLeader;
    }

    public List<String> getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(List<String> idMembers) {
        this.idMembers = idMembers;
    }
}
