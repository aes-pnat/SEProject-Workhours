package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.*;

/**
 * Class Group represents a group of employees who work on given tasks.
 * Director creates groups and assigns them jobs.
 * Every group has a leader. He assigns tasks for the given job.
 * @author Bernard
 */
@Entity
public class Group {

	/**
	 * Group identifier.
	 * Generated value.
	 */
	@Id
	@GeneratedValue
	private Long idGroup;

	/**
	 * Group name.
	 */
	private String name;

	/**
	 * Leader of a group.
	 */
	@ManyToOne()
	@JoinColumn(name = "idGroup", nullable = false)
	private Employee leader;

	/**
	 * Members of a group.
	 */
	@ManyToMany()
	@JoinTable(
			name = "EmployeeGroup",
			joinColumns = @JoinColumn(name = "idGroup"),
			inverseJoinColumns = @JoinColumn(name = "idGroup"))
	private Set<Employee> members;

	/**
	 * Job assigned to a group.
	 */
	@ManyToOne
	@JoinColumn(name = "idJob", nullable = false)
	private Job assignedJob;
}
