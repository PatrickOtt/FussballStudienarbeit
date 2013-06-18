package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Goal
 *
 */
@Entity
@Table(name="goal")
public class Goal implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int id;
	private int matchId;
	private String goalGetterName;
	private int goalMinute;
	
	public Goal() {
		super();
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getGoalGetterName() {
		return goalGetterName;
	}

	public void setGoalGetterName(String goalGetterName) {
		this.goalGetterName = goalGetterName;
	}

	public int getGoalMinute() {
		return goalMinute;
	}

	public void setGoalMinute(int goalMinute) {
		this.goalMinute = goalMinute;
	}
}
