package de.professional_webworkx.studienarbeit.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Result
 *
 */
@Entity
@Table(name="result")
public class Result implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int id;
	private int matchID;
	private int pointsHome;
	private int pointsGuest;
	private String resultName;
	
	
	public Result() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getMatchID() {
		return matchID;
	}


	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}


	public int getPointsHome() {
		return pointsHome;
	}


	public void setPointsHome(int pointsHome) {
		this.pointsHome = pointsHome;
	}


	public int getPointsGuest() {
		return pointsGuest;
	}


	public void setPointsGuest(int pointsGuest) {
		this.pointsGuest = pointsGuest;
	}


	public String getResultName() {
		return resultName;
	}


	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
}

