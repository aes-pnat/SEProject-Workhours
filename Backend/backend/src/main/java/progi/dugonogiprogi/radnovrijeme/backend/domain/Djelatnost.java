package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Djelatnost predstavlja djelatnost koju djelatnici odraduju i
 * strukturu istoimenog entiteta iz baze podataka.
 * Djelatnost stvara i zadaje direktor.
 * @author Bernard
 *
 */
@Entity
public class Djelatnost {

	/**
	 * Jedinstevni id djelatnosti.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Naziv djelatnosti.
	 */
	private String naziv;


	/**
	 * Opis djelatnosti.
	 */
	private String opis;

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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
}
