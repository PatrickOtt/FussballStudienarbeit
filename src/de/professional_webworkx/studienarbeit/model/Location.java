package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity
@Table(name="location")
public class Location implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int id;
	private String locationCity;
	private String locationStadium;
	
	public Location() {
		super();
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationStadium() {
		return locationStadium;
	}

	public void setLocationStadium(String locationStadium) {
		this.locationStadium = locationStadium;
	}
   
	
}
