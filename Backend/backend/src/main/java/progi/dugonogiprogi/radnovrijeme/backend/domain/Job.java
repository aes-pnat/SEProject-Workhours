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

	/**
	 * Gets the ID of the job.
	 *
	 * @return ID of the job
	 */
	public Long getIdJob() {
		return idJob;
	}

	/**
	 * Sets the ID of the job.
	 *
	 * @param idJob ID of the job
	 */
	public void setIdJob(Long idJob) {
		this.idJob = idJob;
	}

	/**
	 * Gets the name of the job.
	 *
	 * @return name of the job
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the job.
	 *
	 * @param name name of the job
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the job description.
	 *
	 * @return a String containing the description of the job
 	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the job description.
	 *
	 * @param description decription of the job
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets a collection of group that have this job assigned.
	 *
	 * @return a Set of Groups
	 */
	public Set<Group> getGroupsWithJob() {
		return groupsWithJob;
	}

	/**
	 * Sets a collection of group that have this job assigned.
	 *
	 * @param groupsWithJob a set of groups
	 */
	public void setGroupsWithJob(Set<Group> groupsWithJob) {
		this.groupsWithJob = groupsWithJob;
	}

	/**
	 * Gets a collection of tasks that are parts of the job
	 *
	 * @return a set of tasks
	 */
	public Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * Sets a collection of tasks that are parts of the job.
	 *
	 * @param tasks a set of tasks
	 */
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * The default constructor.
	 */
	public Job() {

	}

	/**
	 * Creates a new job.
	 *
	 * @param jobName name of the new job
	 * @param jobDescription description of the new job
	 */
	public Job(String jobName, String jobDescription) {
		this.name = jobName;
		this.description = jobDescription;
	}
}
