package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;

public class Team implements Serializable {

	/**
	 * Team-Entity
	 */
	private static final long serialVersionUID = 1L;

	private int teamID;
	private String teamName;
	private String teamIconURL;
	private String stadion;
	
	public Team() {
		super();
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamIconURL() {
		return teamIconURL;
	}

	public void setTeamIconURL(String teamIconURL) {
		this.teamIconURL = teamIconURL;
	}

	public String getStadion() {
		return stadion;
	}

	public void setStadion(String stadion) {
		this.stadion = stadion;
	}
	
	
}
