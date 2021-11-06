package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.sql.Date;

import javax.persistence.*;

/*
 * UnosRadnihSati predstavlja odradene radne sate 
 * djelatnika na odredenom zadatku i strukturu istoimenog
 * entiteta iz baze podataka.
 */
public class UnosRadnihSati {
	
	/*
	 * Jedinstveni id podatka od radnim satima.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private String id;
	
	/*
	 * Datum unosenja radnih sati.
	 */
	private Date datum;
	
	/*
	 * Broj unesenih radnih sati.	
	 */
	private String brRadnihSati;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
