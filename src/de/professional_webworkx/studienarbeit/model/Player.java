package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int playerID;
	private String firstName;
	private String lastName;
	private Team teamID;
	
	// diesen Konstruktor brauchen wir nachher, wenn wir auf JPA umbauen
	public Player() {
		super();
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Team getTeamID() {
		return teamID;
	}

	public void setTeamID(Team teamID) {
		this.teamID = teamID;
	}
}
