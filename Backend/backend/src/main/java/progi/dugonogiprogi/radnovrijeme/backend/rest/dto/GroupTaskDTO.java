package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;


import progi.dugonogiprogi.radnovrijeme.backend.domain.Group;
import progi.dugonogiprogi.radnovrijeme.backend.domain.Task;

/**
 * This data transfer object contains data about tasks and groups who perform them.
 */
public class GroupTaskDTO {

    /**
     * Information about task
     */
    private Task task;
    /**
     * Group that performs the task
     */
    private Group group;

    /**
     * Getter for a task
     *
     * @return task
     */
    public Task getTask() {
        return task;
    }

    /**
     * Setter for a task
     * @param task given reference to a task
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Getter for a group
     * @return requested Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Setter for a group
     *
     * @param group given reference to a group
     */
    public void setGroup(Group group) {
        this.group = group;
    }
}
