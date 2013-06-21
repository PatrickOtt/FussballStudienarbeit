package de.professional_webworkx.studienarbeit.model;
// Tippfehler :-(
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=Team.GET_ALL_TEAMS, query="Select t From Team t"),
	@NamedQuery(name=Team.GET_TEAM_BY_ID, query="Select t From Team t WHERE t.teamID = :teamID")
})
@Entity
@Table(name="team")
public class Team implements Serializable {

	/**
	 * Team-Entity
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_TEAMS	= "Team.get_all_teams";
	public static final String GET_TEAM_BY_ID	= "Team.get_team_by_id";
	

	private int id;
	private String teamName;
	private String teamIconURL;
	private String stadion;
	private List<Player> players = new ArrayList<Player>();
	
	public Team() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	// da unsere Spalte für die PrimaryKey id und nicht teamID heißt, müssen wir
	// noch eine Annotation an den Getter von teamID schreiben:
	@Column(name="id")
	public int getTeamID() {
		return id;
	}

	public void setTeamID(int teamID) {
		this.id = teamID;
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

	@OneToMany(mappedBy="teamID", fetch=FetchType.EAGER)
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
}
