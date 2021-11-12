package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Class job represents job that employees are working on.
 * Director creates and assigns jobs to groups.
 * @author Bernard
 *
 */
@Entity
public class Job {

	/**
	 * Job identifier.
	 * Generated value.
	 */
	@Id
	@GeneratedValue
	private Long idJob;

	/**
	 * Job name.
	 */
	@NotNull
	private String name;


	/**
	 * Job description.
	 */
	private String description;

	/**
	 * Groups that this job is assigned to.
	 */
	@OneToMany(mappedBy = "assignedJob")
	private Set<Group> groupsWithJob;

	/**
	 * Job that this task is part of.
	 */
	@OneToMany(mappedBy = "belongsTo")
	private Set<Task> tasks;

	public Long getIdJob() {
		return idJob;
	}

	public void setIdJob(Long idJob) {
		this.idJob = idJob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Group> getGroupsWithJob() {
		return groupsWithJob;
	}

	public void setGroupsWithJob(Set<Group> groupsWithJob) {
		this.groupsWithJob = groupsWithJob;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Job() {

	}

	public Job(String jobName, String jobDescription) {
		this.name = jobName;
		this.description = jobDescription;
	}
}
