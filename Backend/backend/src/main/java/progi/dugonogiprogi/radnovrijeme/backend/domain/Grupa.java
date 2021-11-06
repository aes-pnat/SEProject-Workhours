package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Klasa Grupa predstavlja grupu djelatnika koji rade na zadanim djelatnostima.
 * Grupu stvara direktor i zadaje joj djelatnosti.
 * Svaka grupa ima svog voditelja i on odreduje zadatke za pojedine
 * djelatnosti.
 * @author Bernard
 *
 */
@Entity
public class Grupa {

	/**
	 * Jedinstveni id grupe.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Naziv grupe.
	 */
	private String naziv;

	@ManyToOne
	private Djelatnik voditelj;

	@ManyToMany()
	private Set<Djelatnik> clanovi;

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
}
