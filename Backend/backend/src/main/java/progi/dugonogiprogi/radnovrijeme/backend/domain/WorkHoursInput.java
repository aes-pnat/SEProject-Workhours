package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.sql.Date;
import java.util.Set;

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
	private Date datum;
	
	/**
	 * Number of hours done.
	 */
	@NotNull
	private String brRadnihSati;

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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getBrRadnihSati() {
		return brRadnihSati;
	}

	public void setBrRadnihSati(String brRadnihSati) {
		this.brRadnihSati = brRadnihSati;
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
