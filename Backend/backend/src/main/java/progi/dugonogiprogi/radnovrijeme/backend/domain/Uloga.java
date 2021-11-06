package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;

/*
 * Uloga predstavlja istoimeni entitet iz baze podataka.
 */
@Entity
public class Uloga {
	
	/*
	 * Jedinstveni id uloge
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/*
	 * Naziv uloge
	 */
	private String naziv;

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
