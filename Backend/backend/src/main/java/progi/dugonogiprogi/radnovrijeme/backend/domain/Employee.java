package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Klasa Djelatnik predstavlja zaposlenika i strukturu istoimenog
 * entiteta iz baze podataka.
 * @author Bernard
 */
@Entity
public class Employee {

	/**
	 * Korisnicko ime profila djelatnika.
	 */
	@Id
	private String korisnickoIme;

	/**
	 * Lozinka profila djelatnika.
	 */
	private String lozinka;

	/**
	 * OIB djelatnika.
	 */
	@Column(unique = true)
	@NotNull
	@Size(min = 11, max = 11)
	private String oib;

	/**
	 * E-mail djelatnika.
	 */
	@Column(unique = true)
	@NotNull
	private String email;

	/**
	 * Ime djelatnika.
	 */
	private String ime;

	/**
	 * Prezime djelatnika.
	 */
	private String prezime;

	@ManyToOne
	@JoinColumn(name = "idEmployee", nullable = false)
	private Role role;

	@OneToMany
	private Set<Group> jeVoditelj;

	@ManyToMany
	private Set<Group> jeClan;
	
	@ManyToMany
	private Set<Task> zadaci;
	
	@OneToMany
	private Set<WorkHoursInput> uneseniRadniSati;
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
