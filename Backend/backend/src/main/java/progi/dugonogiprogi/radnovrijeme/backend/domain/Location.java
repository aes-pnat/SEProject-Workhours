package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Class location represents a location that is assigned to a job
 * that employee needs to do outside of company.
 * @author Bernard
 */
@Entity
public class Location {

	/**
	 * Location identifier.
	 * Generated value.
	 */
	@Id
	@GeneratedValue
	private Long idLocation;

	/**
	 * Location longitude.
	 */
	@NotNull
	private Float longitude;

	/**
	 * Location latitude.
	 */
	@NotNull
	private Float latitude;

	/**
	 * Name of the place that is on this location.
	 */
	private String placeName;

	/**
	 * Address of location.
	 */
	private String address;

	/**
	 * All tasks that are on this location.
	 */
	@OneToMany(mappedBy = "location")
	private Set<Task> locationOfTask;

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Task> getLocationOfTask() {
		return locationOfTask;
	}

	public void setLocationOfTask(Set<Task> locationOfTask) {
		this.locationOfTask = locationOfTask;
	}
}
