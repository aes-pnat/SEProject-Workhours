package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
  * Klasa zadatak predstavlja zadatak koji je voditelj grupe zadao za 
  * pojedinu djelatnost i takoder predstavlja istoimeni entitet iz baze podataka.
  * @author Bernard
  */
@Entity
public class Task {

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

	@ManyToOne
	@JoinColumn(name = "idTask", nullable = false)
	private Job belongsTo;

	@ManyToOne
	@JoinColumn(name = "idTask", nullable = false)
	private Location location;

    @ManyToMany(mappedBy = "tasks")
    private List<Employee> doingThisTask;
}
