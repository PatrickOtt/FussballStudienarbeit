<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="de.professional_webworkx.studienarbeit.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste aller Spieler</title>
</head>
<body>
<%
	List<Player> players = (List<Player>) request.getAttribute("players");
%>
<h1>Alle Spieler der 1. Bundesliga</h1> <a href="index.jsp">zur&uuml;ck zur Startseite</a>
<ul>
	<c:forEach items="#{players}" var="player">
		<li>${player.lastName } &nbsp; ${player.teamID.teamName }</li>
	</c:forEach>
</ul>
</body>
</html>