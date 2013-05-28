package de.professional_webworkx.studienarbeit.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.professional_webworkx.studienarbeit.db.DbOpenHandler;

public class XMLParser {

	private static String XML = "xml/teams.xml";
	private static DbOpenHandler dbOpenHandler;
	
	public static void main(String[] args) {
		
		SAXBuilder builder 	= new SAXBuilder();
		File xmlFile		= new File(XML);
		dbOpenHandler		= DbOpenHandler.getDatabaseInstance();
		
		try {
			Document document	= builder.build(xmlFile);
			Element rootNode	= document.getRootElement();
			List<Element> children = rootNode.getChildren();
			for(Element child : children) {
				int teamID		= Integer.parseInt(child.getChildText("teamID"));
				String teamName = child.getChildText("teamName");
				String teamIcon	= child.getChildText("teamIconURL");
				String stadion	= child.getChildText("stadion");
		
				dbOpenHandler.insertTeam(teamID, teamName, teamIcon, stadion);
				
				List<Element> players = child.getChildren("player");
				for(Element player : players) {
					
					String name	= player.getText();
					dbOpenHandler.insertPlayer(name, teamID);
				}
				System.out.println(teamName + " wurde hinzugefügt.");
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
