package progi.dugonogiprogi.radnovrijeme.backend.domain;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;


/**
 * UnosRadnihSati predstavlja odradene radne sate 
 * djelatnika na odredenom zadatku i strukturu istoimenog
 * entiteta iz baze podataka.
 * @author Bernard
 */
@Entity
public class WorkHoursInput {
	
	/**
	 * Jedinstveni id podatka od radnim satima.
	 * Generirana vrijednost.
	 */
	@Id
	@GeneratedValue
	private Long idWorkHoursInput;
	
	/**
	 * Datum unosenja radnih sati.
	 */
	private Date datum;
	
	/**
	 * Broj unesenih radnih sati.	
	 */
	private String brRadnihSati;

	@ManyToOne
	@JoinColumn(name = "idWorkHoursInput", nullable = false)
	private Employee hasDone;
	
}
