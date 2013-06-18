<%@page import="de.professional_webworkx.studienarbeit.db.DbOpenHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="de.professional_webworkx.studienarbeit.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	// TeamID aus der URL extrahieren
	String teamString 		= request.getParameter("teamid");
	// teamID aus dem String bilden
	int teamID				= -1;
	try {
		teamID				= Integer.parseInt(teamString);
	} catch(NumberFormatException ex) {
		teamID				= 40;
	}
	// Datenbank-Handler besorgen
	DbOpenHandler handler	= DbOpenHandler.getDatabaseInstance();
	
	List<Player> players	= handler.getAllPlayerByTeamId(teamID);
	// anhand der teamID eine Query gegen die Datenbank und das Team-Object zurückliefern lassen
	Team team				= handler.getTeamByID(teamID); 
	
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detailansicht</title>
</head>
<body>

	<h1>Du willst Details zu Team: <%=teamID %></h1>
	<strong>Teamname: </strong> <%=team.getTeamName() %><br/>
	<strong>TeamLogo: </strong> <img src="<%= team.getTeamIconURL() %>"><br/>
	<strong>Teamstadion: </strong> <%= team.getStadion() %>
	<hr/>
	<strong>Spieler bei <%= team.getTeamName() %></strong><br/>
	<!-- 
		warum die forEach-Schleife jetzt nicht funktioniert, kann ich mir noch
		nicht erklären, doch um das Beispiel nun abzuschließen verwenden wir
		einfach eine Java forEach-Schleife..
	 -->
	 <ul>
		<%
			for(Player p : players) { %>
				<li><%=p.getLastName() %></li>
			<%} %>
	</ul>
</body>
</html>