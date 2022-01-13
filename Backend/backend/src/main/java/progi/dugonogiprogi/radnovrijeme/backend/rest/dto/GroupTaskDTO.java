package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;


import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

public class GroupTaskDTO {

    private Task task;

    private Group group;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
