<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="de.professional_webworkx.studienarbeit.model.*" %>
<!-- Import der JSTL Core Libary -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bundesliga Vereine</title>
</head>
<body>
<!-- Das ist ein HTML Kommentar (mehrzeilig) -->
<!-- 
	Damit wir nun Zugriff auf unsere Liste mit Vereinen bekommen, holen wir uns diese
	vom request-Object, indem wir dort dann mit getAttribute() auf die im Servlet hinzugefügte
	Liste ("teams") zugreifen
 -->
 <%
 	// innerhalb dieser <% sind wir wieder auf der Java-Seite, d.h. wir können
 	// wie gewohnt mit Java arbeiten
 	// die fehlenden Imports werden per @ page import aufgelöst
 	
 	// Das Stück Code bleibt.
 	List<Team> teams = (List<Team>) request.getAttribute("teams");
 %>
 <!-- 
 	Jetzt haben wir also, nachdem das Servlet über den Browser aufgerufen wurde, die Liste
 	unserer Mannschaften zur Verfügung. Jetzt müssen die noch ausgegeben werden. 
 	Dazu verwende ich nun mal eine unsortierte Liste, man könnte sie alle auch wieder
 	in Tabellenform darstellen, wie im Servlet gesehen.	
-->
<!-- Noch eine Ü*berschrift -->
<h1>Vereine der 1. Bundesliga</h1>
<ul>
	<c:forEach items="#{teams}" var="team">
	<!-- Damit ist nun die foreach-Schleife aus der Java-Welt gänzlich verschwunden und der JSP Bearbeiter
		wird sich sicher freuen wenn er von Java nix mehr wissen muss, sondern seine Energie 
		auf den HTML Teil konzentrieren kann.
		Versuchen wir mal eben noch das Logo einzubinden, mal sehen ob's klappt.
	 -->
		<li>${team.teamName }&nbsp; <img src="${team.teamIconURL }"></li>
	</c:forEach>
</ul>

</body>
</html>