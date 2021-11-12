package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * WorkHoursInput represents work hours that
 * an employee has done in a day on a specified task.
 * @author Bernard
 */
@Entity
public class WorkHoursInput {
	
	/**
	 * WorkHoursInput identifier.
	 * Generated value.
	 */
	@Id
	@GeneratedValue
	private Long idWorkHoursInput;
	
	/**
	 * Date of data input.
	 */
	@NotNull
	private Date date;
	
	/**
	 * Number of hours done.
	 */
	@NotNull
	private String workHoursDone;

	/**
	 * Employee that inputted work hours.
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idWorkHoursInput", nullable = false)
	private Employee hasDone;

	/**
	 * Task that employee worked on.
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idWorkHoursInput", nullable = false)
	private Task onTask;

	public Long getIdWorkHoursInput() {
		return idWorkHoursInput;
	}

	public void setIdWorkHoursInput(Long idWorkHoursInput) {
		this.idWorkHoursInput = idWorkHoursInput;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWorkHoursDone() {
		return workHoursDone;
	}

	public void setWorkHoursDone(String workHoursDone) {
		this.workHoursDone = workHoursDone;
	}

	public Employee getHasDone() {
		return hasDone;
	}

	public void setHasDone(Employee hasDone) {
		this.hasDone = hasDone;
	}

	public Task getOnTask() {
		return onTask;
	}

	public void setOnTask(Task onTask) {
		this.onTask = onTask;
	}
}
