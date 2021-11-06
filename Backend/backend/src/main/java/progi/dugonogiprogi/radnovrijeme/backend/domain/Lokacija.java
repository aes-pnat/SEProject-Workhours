package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Klasa Lokacija lokaciju zadatka koji je izvan mjesta poslovanja
 * i strukturu istoimenog entiteta iz baze podataka.
 * @author Bernard
 */
@Entity
public class Lokacija {

	/**
	 * Jedinstveni id lokacije.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Longituda lokacije.
	 */
	private Float longitude;

	/**
	 * Latituda lokacije.
	 */
	private Float latitude;

	/**
	 * Naziv mjesta koja se nalazi na lokaciji.
	 */
	private String mjesto;

	/**
	 * Adresa koja se nalazi na lokaciji.
	 */
	private String adresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
}
