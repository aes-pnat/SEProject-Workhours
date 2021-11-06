package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * UnosRadnihSati predstavlja odradene radne sate 
 * djelatnika na odredenom zadatku i strukturu istoimenog
 * entiteta iz baze podataka.
 * @author Bernard
 */
@Entity
public class UnosRadnihSati {
	
	/**
	 * Jedinstveni id podatka od radnim satima.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Datum unosenja radnih sati.
	 */
	private Date datum;
	
	/**
	 * Broj unesenih radnih sati.	
	 */
	private String brRadnihSati;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
