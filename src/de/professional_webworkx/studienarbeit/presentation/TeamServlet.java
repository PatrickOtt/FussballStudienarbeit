package de.professional_webworkx.studienarbeit.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.itextpdf.text.Document;

import de.professional_webworkx.studienarbeit.business.TeamService;
import de.professional_webworkx.studienarbeit.db.DbOpenHandler;
import de.professional_webworkx.studienarbeit.model.Team;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet(name = "teams", description = "Auf diesen Namen hört das Servlet nachher im Browser.", urlPatterns = { "/teams" })
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Durch die Annotation @EJB wird der CDI Mechanismus beansprucht und wenn ein
	 * TeamService Object benötigt wird, erhalten wir durch den Glassfish dieses 
	 * Object vollautomatisch und ganz bequem injeziert und können auf die 
	 * public Methoden daraus zugreifen 
	 */
	
	@EJB
	private TeamService service;
	
	// private static DbOpenHandler dbOpenHandler;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * processRequest(HttpServletRequest request, HttpServletResponse response)
     * Diese Methode übernimmt die Arbeit von den beiden untenstehenden Methoden, 
     * wie vielleicht noch aus RN 1 bekannt ist, gibt's in HTTP verschiedene Verben,
     * die verwendet werden, u.a. POST oder GET und diese beiden Methoden haben
     * wir schon im Servlet drin. Damit wir den Code nicht doppelt schreiben müssen,
     * bauen wir diese processRequest Methode und leiten die Aufrufe von doGet und doPost
     * weiter.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	
    	// Contenttyp auf html setzen
    	// das wird auf dem response-Object gemacht, weil wir ja vom Server die
    	// response auf unseren Request bekommen. Ohne Request kein Response
    	response.setContentType("text/html");
    	
    	
    	// DB-Zugriff
    	// hier holen wir die Handler-Instance
    	//dbOpenHandler = DbOpenHandler.getDatabaseInstance();
    	// Liste der Teams aus der Datenbank holen
    	List<Team> allTeams = service.getAllTeams();
    	
    	// hierzu ein paar kleine Änderungen im Servlet.
    	/*
    	 * An unser request-Object hängen wir mit setAttribute() die Liste unserer Vereine an
    	 */
    	request.setAttribute("teams", allTeams);
    	
    	// Damit die Zuständigkeit an die JSP Datei übergeben wird, besorgen wir uns einen
    	// RequestDispatcher, der den Request des Clients dann an die JSP übergeben kann
    	// damit sagen wir dem requestDispatcher gleich an welches File er nachher
    	// sein request und response-Object übergeben soll.
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("teams.jsp");
    	
    	// zur Weiterleitung
    	// hier kann noch was passieren, daher try-catch
    	try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	// Und da nun unser Servlet nur noch die Daten besorgt, aber nichts mehr
    	// anzeigen soll, nehmen wir den ganzen Teil mit out.write() ... raus
    	
    	
    	// Jetzt kommt der HTML Teil
    	
    	// Mit dem PrintWriter können wir aus dem Servlet rausschreiben
    	// Klar, hier kann wieder eine Exception auftreten, die fangen wir
    	
    	// unsere bisherige WebAnwendung basiert auf einer Servlet-only
    	// Variante, doch wie nachfolgend zu sehen ist, besteht der gesamte 
    	// Response-Teil (fast) aus Strings, die nicht überprüft werden, das ist ja auch
    	// nicht die Aufgabe des Compilers, der kennt sich nur mit Java-Code aus
    	
    	
    	// den hier folgenden HTML Teil kommentieren wir aus
    	/*
    	PrintWriter out = null;
    	try {
			out = response.getWriter();
			out.write("<html>");
			out.write("<head><title>Liste unserer Vereine</title></head>");
			out.write("<body>");
			out.write("<h1>Vereine der 1. Bundesliga</h1>");
			out.write("<table>");
			out.write("<tr>");
			out.write("<th>VereinsID</th>");
			out.write("<th>Vereinsname</th>");
			out.write("<th>Vereins-Logo</th>");
			out.write("<th>Vereins-Stadion</th>");
			out.write("</tr>");
			// das war bisher der übliche HTML Vorspann und die Erstellung der Kopfzeile
			// einer Tabelle mit 4 Spalten (ID, Name, Logo und Stadion)
			
			// Jetzt laufen wir durch unsere Team-liste und zeigen die einzelnen Properties an 
			for(Team team : allTeams) {
				// <tr> erzeugt eine neue Zeile TableRow
				out.write("<tr>");
				// <td> dann eine neue Spalte
				out.write("<td>"+team.getTeamID()+"</td>");
				out.write("<td>"+team.getTeamName()+"</td>");
				out.write("<td><img src=\""+team.getTeamIconURL()+"\" title="+team.getTeamName()+"/></td>");
				out.write("<td>"+team.getStadion()+"</td>");
				out.write("</tr>");
			}
			
			// wenn wir durch die Liste durch sind, muss die Tabelle noch geschlossen und 
			// auch das HTML Dokument geschlossen werden
			out.write("</table>");
			// Das eben markierte soll alles im JSP File sein, dazu packen wir die Teams-Liste
			// und stecken sie in das request-Object.
			out.write("</body>");
			out.write("</html>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(out != null) {
				// nicht zwingend nötig
				out.close();
			}
		}
		*/
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Tipphilfe: pro schreiben und dann STRG+LEERTASTE drücken.. ;-)
		processRequest(request, response);
	}

}
