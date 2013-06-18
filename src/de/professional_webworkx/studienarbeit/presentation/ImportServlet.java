package de.professional_webworkx.studienarbeit.presentation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.professional_webworkx.studienarbeit.xml.MatchesParser;

/**
 * Servlet implementation class ImportServlet
 */
@WebServlet("/importXML")
public class ImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private MatchesParser matchesParser;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if(matchesParser.parseXML() == true) {
    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("import.jsp");
    		requestDispatcher.forward(request, response);
    	}
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
		processRequest(request, response);
	}

}
