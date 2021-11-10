package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Klasa Uloga predstavlja ulogu djelatnika i strukturu istoimenog
 * entiteta iz baze podataka.
 * @author Bernard
 */
@Entity
public class Role {

	/**
	 * Jedinstveni id uloge
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Naziv uloge.
	 */
	private String naziv;

	/**
	 * Svi djelatnici koji imaju odredenu ulogu.
	 */
	@OneToMany
	private Set<Djelatnik> ulogaDjelatnika;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Djelatnik> getUlogaDjelatnika() {
		return ulogaDjelatnika;
	}

	public void setUlogaDjelatnika(Set<Djelatnik> ulogaDjelatnika) {
		this.ulogaDjelatnika = ulogaDjelatnika;
	}
}
