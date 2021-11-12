package progi.dugonogiprogi.radnovrijeme.backend.rest;

import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

import java.util.List;

public class TasksDTO {
    private String groupName;
    private List<Task> taskForThisGroup;

    public TasksDTO(String groupName, List<Task> taskForThisGroup) {
        this.groupName = groupName;
        this.taskForThisGroup = taskForThisGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Task> getTaskForThisGroup() {
        return taskForThisGroup;
    }

    public void setTaskForThisGroup(List<Task> taskForThisGroup) {
        this.taskForThisGroup = taskForThisGroup;
    }
}
