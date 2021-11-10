package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class Task represents a task
 * that leader of a group has given to a group for their job.
 * @author Bernard
 */
@Entity
public class Task {

	/**
	 * Task idetifier.
	 * Generated value.
	 */
	@Id
	@GeneratedValue
	private Long idTask;

	/**
	 * Task name.
	 */
	@NotNull
	private String name;

	/**
	 * Task description.
	 */
	private String description;

	/**
	 * Date and time when a task started.
	 */
	@NotNull
	@DateTimeFormat
	private Date dateTimeStart;

	/**
	 * Date and time when a task ended.
	 */
	@DateTimeFormat
	private Date dateTimeEnd;

	/**
	 * Estimate of hours needed to complete a task.
	 */
	private String hoursNeededEstimate;

	/**
	 * Planned profit on a task.
	 */
	private Float plannedProfit;

	/**
	 * Realized profit on a task.
	 */
	private Float realizedProfit;

	/**
	 * Planned cost of a task.
	 */
	private Float plannedCost;

	/**
	 * Realized cost of a task.
	 */
	private Float realizedCost;

	/**
	 * Job that this task belongs to.
	 */
	@ManyToOne
	@JoinColumn(name = "idTask", nullable = false)
	private Job belongsTo;

	/**
	 * If a task needs to be completed outside of company,
	 * this value stores location of a task.
	 */
	@ManyToOne
	@JoinColumn(name = "idTask", nullable = false)
	private Location location;

	/**
	 * Set of employees that are assigned to this task.
	 */
    @ManyToMany(mappedBy = "tasks")
    private List<Employee> doingThisTask;

	/**
	 * Hours that an employee spent on this task in a day.
	 */
	@OneToMany(mappedBy = "onTask")
	private Set<WorkHoursInput> hoursInputs;

	public Long getIdTask() {
		return idTask;
	}

	public void setIdTask(Long idTask) {
		this.idTask = idTask;
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

	public Date getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Date dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public Date getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Date dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public String getHoursNeededEstimate() {
		return hoursNeededEstimate;
	}

	public void setHoursNeededEstimate(String hoursNeededEstimate) {
		this.hoursNeededEstimate = hoursNeededEstimate;
	}

	public Job getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(Job belongsTo) {
		this.belongsTo = belongsTo;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Employee> getDoingThisTask() {
		return doingThisTask;
	}

	public void setDoingThisTask(List<Employee> doingThisTask) {
		this.doingThisTask = doingThisTask;
	}

	public Set<WorkHoursInput> getHoursInputs() {
		return hoursInputs;
	}

	public void setHoursInputs(Set<WorkHoursInput> hoursInputs) {
		this.hoursInputs = hoursInputs;
	}

	public Float getPlannedProfit() {
		return plannedProfit;
	}

	public void setPlannedProfit(Float plannedProfit) {
		this.plannedProfit = plannedProfit;
	}

	public Float getRealizedProfit() {
		return realizedProfit;
	}

	public void setRealizedProfit(Float realizedProfit) {
		this.realizedProfit = realizedProfit;
	}

	public Float getPlannedCost() {
		return plannedCost;
	}

	public void setPlannedCost(Float plannedCost) {
		this.plannedCost = plannedCost;
	}

	public Float getRealizedCost() {
		return realizedCost;
	}

	public void setRealizedCost(Float realizedCost) {
		this.realizedCost = realizedCost;
	}
}
