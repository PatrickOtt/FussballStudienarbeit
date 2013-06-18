package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Match
 *
 */

@NamedQueries({
	@NamedQuery(name=Match.GET_ALL_MATCHES, query="Select m From Match m")
})

@Entity
@Table(name="matches")
public class Match implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	// Query-Konstanten
	public static final String GET_ALL_MATCHES	= "Match.get_all_matches";

	private int id;
	private Date matchDate;
	private int homeTeam;
	private int guestTeam;
	private int numberOfViewers;
	
	public Match() {
		super();
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public int getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(int homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(int guestTeam) {
		this.guestTeam = guestTeam;
	}

	public int getNumberOfViewers() {
		return numberOfViewers;
	}

	public void setNumberOfViewers(int numberOfViewers) {
		this.numberOfViewers = numberOfViewers;
	}
   
	
}
