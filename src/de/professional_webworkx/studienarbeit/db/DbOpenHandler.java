package de.professional_webworkx.studienarbeit.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PrePersist;

import com.mysql.jdbc.PreparedStatement;

import de.professional_webworkx.studienarbeit.model.Player;
import de.professional_webworkx.studienarbeit.model.Team;

public class DbOpenHandler {

	private static final String USER	= "fussball";
	private static final String PASS	= "fussball";
	private static final String DB	= "tutorial";
	private static final String HOST	= "localhost";
	private static final String URL	= "jdbc:mysql://"+HOST+":3306/"+DB;
	
	// Queries
	private static final String GET_ALL_TEAMS			= "SELECT * FROM team";
	private static final String GET_ALL_PLAYERS		= "SELECT * FROM player";
	private static final String GET_PLAYER_BY_TEAM	= "SELECT * FROM player WHERE teamID = ";
	private static final String GET_TEAM_BY_ID 		= "SELECT * FROM team WHERE id = ";
	
	// Team-Columns
	private static final String TEAM_TEAMID	= "id";
	private static final String TEAM_TEAMNAME	= "teamName";
	private static final String TEAM_TEAMICON	= "teamIconURL";
	private static final String TEAM_STADION	= "stadion";
	
	// Player-Columns
	private static final String PLAYER_ID			= "id";
	private static final String PLAYER_FIRSTNAME	= "firstName";
	private static final String PLAYER_LASTNAME	= "lastName";
	private static final String PLAYER_TEAMID		= "teamID";
	
	
	
	private static DbOpenHandler dbOpenHandler;
	private Connection connection;
	
	private DbOpenHandler() {
		connect();
	}

	private void connect() {
		
		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static DbOpenHandler getDatabaseInstance() {
		if(dbOpenHandler == null) {
			dbOpenHandler = new DbOpenHandler();
		}
		return dbOpenHandler;
	}
	
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				dbOpenHandler = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertTeam(int teamID, String teamName, String teamIconURL, String stadion) {
		
		try {
			PreparedStatement preparedStatement = 
					(PreparedStatement) connection.prepareStatement("INSERT INTO team VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, teamID);
			preparedStatement.setString(2, teamName);
			preparedStatement.setString(3, teamIconURL);
			preparedStatement.setString(4, stadion);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertPlayer(String name, int teamID) {
		
		try {
			PreparedStatement preparedStatement =
					(PreparedStatement) connection.prepareStatement("INSERT INTO player (lastName, teamID) VALUES (?, ?)");
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, teamID);
			
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// mit dieser Methode holen wir uns eine Liste, die wir auf Team getyped haben
	public List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<Team>();
		
		try {
			// wir basteln und ein Statement zusammen und holen uns damit ein 
			// Set an Datensaetzen aus der Datenbank
			// hier stand vorhin Statement, das habe ich durch PreparedStatement ersetzt
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(GET_ALL_TEAMS);
			// Die Konstante GET_ALL_TEAMS haben wir uns erstellt um Tippfehler bei den
			// SELEct Statements zu vermeiden, bei diesem einfach Beispiel geht das jetzt
			// noch, allerdings kann es auch sehr viel komplexer werden.
			ResultSet rs = statement.executeQuery(GET_ALL_TEAMS);
			while(rs.next()) {
				// in der Variablen rs stecken nun alle Teams drin, die in unserer Datenbank rumdümpeln
				// in der while-Schleife gehen wir durch das ResultSet durch und holen und nach und nach
				// die einzelnen Einträge
				
				// da es sich um Teams handelt, basteln wir uns ein neues Team-Object
				Team team = new Team();
				// um nun die Informationen aus dem Set zu holen gibt's 2 Möglichkeiten, entweder
				// man gibt die Spalte als INT Wert an oder man kann auch den Namen der Spalte 
				// angeben. Ich arbeite mit den Namen der Spalten, da ist es gute Praxis, 
				// wenn man sich dafür wieder Konstanten erstellt.
				team.setTeamID(Integer.parseInt(rs.getString(TEAM_TEAMID)));
				team.setTeamName(rs.getString(TEAM_TEAMNAME));
				team.setTeamIconURL(rs.getString(TEAM_TEAMICON));
				team.setStadion(rs.getString(TEAM_STADION));
				
				// nach einem Durchlauf wir das neu erstellte Team-Object an die
				// teams-Liste angefügt
				teams.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// wenn nichts schief ging, dann haben wir nun eine Liste aus Teams, die 
		// 18 Einträge enthalten sollte, diese geben wir als Ergebnis der Methode
		// zurück
		return teams;
	}
	
	public Team getTeamByID(int teamID) {
		
		try {
			PreparedStatement preparedStatement = 
					(PreparedStatement) connection.prepareStatement(GET_TEAM_BY_ID);
			
			ResultSet rs = preparedStatement.executeQuery(GET_TEAM_BY_ID + " " + teamID);
			Team team = new Team();
			while(rs.next()) {
				team.setTeamID(rs.getShort(TEAM_TEAMID));
				team.setTeamName(rs.getString(TEAM_TEAMNAME));
				team.setTeamIconURL(rs.getString(TEAM_TEAMICON));
				team.setStadion(rs.getString(TEAM_STADION));
			}
			return team;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Player> getAllPlayer() {
		
		List<Player> players = new ArrayList<Player>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(GET_ALL_PLAYERS);
			ResultSet rs = preparedStatement.executeQuery(); // Shift + Alt + L
			while(rs.next()) {
				Player player = new Player();
				player.setPlayerID(Integer.parseInt(rs.getString(PLAYER_ID)));
				player.setLastName(rs.getString(PLAYER_LASTNAME));
				player.setTeamID(getTeamByID(Integer.parseInt(rs.getString(PLAYER_TEAMID))));
				players.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}
}
