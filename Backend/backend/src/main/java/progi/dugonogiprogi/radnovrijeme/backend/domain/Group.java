package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.*;

/**
 * Klasa Grupa predstavlja grupu djelatnika koji rade na zadanim djelatnostima.
 * Grupu stvara direktor i zadaje joj djelatnosti.
 * Svaka grupa ima svog voditelja i on odreduje zadatke za pojedine
 * djelatnosti.
 * @author Bernard
 *
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


	@ManyToOne()
	@JoinColumn(name = "idGroup", nullable = false)
	private Employee leader;

	@ManyToMany()
	@JoinTable(
			name = "EmployeeGroup",
			joinColumns = @JoinColumn(name = "idGroup"),
			inverseJoinColumns = @JoinColumn(name = "idGroup"))
	private Set<Employee> members;

	@ManyToOne
	@JoinColumn(name = "idJob", nullable = false)
	private Job assignedJob;
}
