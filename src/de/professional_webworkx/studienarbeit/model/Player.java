package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=Player.GET_ALL_PLAYER, query="Select p From Player p"),
	@NamedQuery(name=Player.GET_PLAYER_BY_ID, query="Select p From Player p WHERE p.playerID = :playerId")
	})

@Entity
@Table(name="player")
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Named-Query-Konstanten
	public static final String GET_ALL_PLAYER 	= "Player.get_all_player";
	public static final String GET_PLAYER_BY_ID	= "Player.get_player_by_id";
	
	private int id;
	private String firstName;
	private String lastName;
	private Team teamID;
	
	// diesen Konstruktor brauchen wir nachher, wenn wir auf JPA umbauen
	public Player() {
		super();
	}

	// Primary Key in der Tabelle player
	// die Zahlen für den Schlüssel werden Automatisch generiert
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int getPlayerID() {
		return id;
	}

	public void setPlayerID(int playerID) {
		this.id = playerID;
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="teamID")
	public Team getTeamID() {
		return teamID;
	}

	public void setTeamID(Team teamID) {
		this.teamID = teamID;
	}
}
