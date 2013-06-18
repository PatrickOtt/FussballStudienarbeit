package de.professional_webworkx.studienarbeit.presentation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.professional_webworkx.studienarbeit.business.PlayerService;
import de.professional_webworkx.studienarbeit.model.Player;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/players")
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private PlayerService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

    	
    	List<Player> players = service.getAllPlayer();
    	request.setAttribute("players", players);
    	
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("player.jsp");
    	try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
