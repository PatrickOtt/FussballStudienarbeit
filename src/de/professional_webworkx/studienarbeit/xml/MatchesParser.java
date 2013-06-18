package de.professional_webworkx.studienarbeit.xml;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import de.professional_webworkx.studienarbeit.model.Goal;
import de.professional_webworkx.studienarbeit.model.Location;
import de.professional_webworkx.studienarbeit.model.Match;
import de.professional_webworkx.studienarbeit.model.Result;
import de.professional_webworkx.studienarbeit.model.Team;

@Stateless(name="xmlParser")
public class MatchesParser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MATCHESXML	= "/home/ottp/MyCloud/SSP-Tutorial/05-Youtube-Tutorials/FussballStudienarbeit/xml/matches.xml";
	
	@PersistenceContext
	private EntityManager em;
	
	public boolean parseXML() {
	
		File xmlFile		= new File(MATCHESXML);
		SAXBuilder builder	= new SAXBuilder();
		try {
			Document document	= builder.build(xmlFile);
			Element rootNode	= document.getRootElement();
			List<Element> rootChildren	= rootNode.getChildren();
			for(Element childrenOfRoot : rootChildren) {
				Match match = new Match();
				match.setId(Integer.parseInt(childrenOfRoot.getChildText("matchID")));
				String s = childrenOfRoot.getChildText("matchDateTime");
				XMLGregorianCalendar xmlGregorianCalendar = XMLGregorianCalendarImpl.parse(childrenOfRoot.getChildText("matchDateTime"));
				GregorianCalendar cal = new GregorianCalendar(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay());
				match.setMatchDate(cal.getTime());
				Team homeTeam = em.find(Team.class, Integer.parseInt(childrenOfRoot.getChildText("idTeam1")));
				Team guestTeam = em.find(Team.class, Integer.parseInt(childrenOfRoot.getChildText("idTeam2")));
				match.setHomeTeam(homeTeam.getTeamID());
				match.setGuestTeam(guestTeam.getTeamID());
				if(childrenOfRoot.getChildText("NumberOfViewers").trim().isEmpty()) 
					match.setNumberOfViewers(0);
				else
					match.setNumberOfViewers(Integer.parseInt(childrenOfRoot.getChildText("NumberOfViewers")));
				
				Logger.getLogger(MatchesParser.class.getSimpleName()).log(Level.INFO, "### Match " + match.getId() + " wird gespeichert");
				if(!em.contains(match)) {
					em.persist(match);
				}
				
				List<Element> matchResult 	= childrenOfRoot.getChildren("matchResults");
				List<Element> matchGoals	= childrenOfRoot.getChildren("goals");
				for(Element e : matchResult) {
					List<Element> results = e.getChildren("matchResult");
					for(Element res : results) {
						Result result = new Result();
						result.setPointsHome(Integer.parseInt(res.getChildText("pointsTeam1")));
						result.setPointsGuest(Integer.parseInt(res.getChildText("pointsTeam2")));
						result.setResultName(res.getChildText("resultName"));
						result.setMatchID(match.getId());
						if(!em.contains(result))
							em.persist(result);
					}
				}
				
				
				for(Element g : matchGoals) {
					List<Element> goal = g.getChildren("Goal");
					for(Element go : goal) {
						Goal goal2 = new Goal();
						goal2.setId(Integer.parseInt(go.getChildText("goalID")));
						goal2.setGoalGetterName(go.getChildText("goalGetterName"));
						goal2.setGoalMinute(Integer.parseInt(go.getChildText("goalMatchMinute")));
						goal2.setMatchId(match.getId());
						if(!em.contains(goal2))
							em.persist(goal2);
					}
				}
				
			}
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
}
