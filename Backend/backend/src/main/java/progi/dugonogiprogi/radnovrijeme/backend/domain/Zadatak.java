package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
  * Klasa zadatak predstavlja zadatak koji je voditelj grupe zadao za 
  * pojedinu djelatnost i takoder predstavlja istoimeni entitet iz baze podataka.
  * @author Bernard
  */
@Entity
public class Zadatak {

	/**
	 * Jedinstveni id zadatka.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Naziv zadatka.
	 */
	private String naziv;

	/**
	 * Opis zadatka.
	 */
	private String opis;

	/**
	 * Datum i vrijeme pocetka zadatka.
	 */
	@DateTimeFormat
	private Date datumVrijemePocetka;

	/**
	 * Datum i vrijeme zavrsetka zadatka.
	 */
	@DateTimeFormat
	private Date datumVrijemeZavrsetka;

	/**
	 * Procjena broja sati potrebnih za izvrsavanje zadatka.
	 */
	private String procjenaBrojaSati;

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

	public Date getDatumVrijemePocetka() {
		return datumVrijemePocetka;
	}

	public void setDatumVrijemePocetka(Date datumVrijemePocetka) {
		this.datumVrijemePocetka = datumVrijemePocetka;
	}

	public Date getDatumVrijemeZavrsetka() {
		return datumVrijemeZavrsetka;
	}

	public void setDatumVrijemeZavrsetka(Date datumVrijemeZavrsetka) {
		this.datumVrijemeZavrsetka = datumVrijemeZavrsetka;
	}

	public String getProcjenaBrojaSati() {
		return procjenaBrojaSati;
	}

	public void setProcjenaBrojaSati(String procjenaBrojaSati) {
		this.procjenaBrojaSati = procjenaBrojaSati;
	}
	

}
