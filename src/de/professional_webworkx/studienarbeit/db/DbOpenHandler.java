package de.professional_webworkx.studienarbeit.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class DbOpenHandler {

	private static final String USER	= "fussball";
	private static final String PASS	= "fussball";
	private static final String DB	= "tutorial";
	private static final String HOST	= "localhost";
	private static final String URL	= "jdbc:mysql://"+HOST+":3306/"+DB;
	
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
	
}
