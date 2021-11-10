package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class employee represents an employee in the company.
 * @author Bernard
 */
@Entity
public class Employee {

	/**
	 * Employee identifier.
	 */
	@Id
	@GeneratedValue
	private Long idEmployee;

	/**
	 * Employee account username.
	 */
	private String username;

	/**
	 * Employee account password.
	 */
	private String password;

	/**
	 * PID of employee.
	 */
	@Column(unique = true)
	@NotNull
	@Size(min = 11, max = 11)
	private String pid;

	/**
	 * E-mail of employee.
	 */
	@Column(unique = true)
	@NotNull
	private String email;

	/**
	 * Employees name.
	 */
	private String name;

	/**
	 * Employees surname.
	 */
	private String surname;

	/**
	 * Employees role.
	 */
	@ManyToOne
	@JoinColumn(name = "idEmployee", nullable = false)
	private Role role;

	/**
	 * Groups that has this employee as leader.
	 */
	@OneToMany(mappedBy = "leader")
	private Set<Group> isLeader;
	/**
	 *
	 */
	@ManyToMany(mappedBy = "members")
	private Set<Group> isMember;

	/**
	 * Tasks given to this employee.
	 */
	@ManyToMany
	@JoinTable(
			name = "EmployeeTask",
			joinColumns = @JoinColumn(name = "idEmployee"),
			inverseJoinColumns = @JoinColumn(name = "idTask"))
	private Set<Task> tasks;

	/**
	 * How many hours has this employee done in a day.
	 */
	@OneToMany(mappedBy = "hasDone")
	private Set<WorkHoursInput> workHours;

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Group> getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Set<Group> isLeader) {
		this.isLeader = isLeader;
	}

	public Set<Group> getIsMember() {
		return isMember;
	}

	public void setIsMember(Set<Group> isMember) {
		this.isMember = isMember;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<WorkHoursInput> getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Set<WorkHoursInput> workHours) {
		this.workHours = workHours;
	}
}