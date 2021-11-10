package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Class Role represents what role an employee has in the company.
 * @author Bernard
 */
@Entity
public class Role {

	/**
	 * Role identifier.
	 * Generated value.
	 */
	@Id
	@GeneratedValue
	private Long idRole;

	/**
	 * Role name.
	 */
	@NotNull
	private String name;

	/**
	 * All employees with this role.
	 */
	@OneToMany(mappedBy = "role")
	private Set<Employee> employeesWithRole;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployeesWithRole() {
		return employeesWithRole;
	}

	public void setEmployeesWithRole(Set<Employee> employeesWithRole) {
		this.employeesWithRole = employeesWithRole;
	}
}
