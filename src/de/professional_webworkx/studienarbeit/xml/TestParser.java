package de.professional_webworkx.studienarbeit.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TestParser {

	private static final String XML = "xml/matches.xml";
	
	public static void main(String[] args) throws JDOMException, IOException {
		
		File xmlFile = new File(XML);
		Document document = new SAXBuilder().build(xmlFile);
		Element root = document.getRootElement();
		List<Element> children = root.getChildren();
		for(Element e : children) {
			System.out.println(Integer.parseInt(e.getChild("location").getChildText("locationID")));
			System.out.println(e.getChild("location").getChildText("locationCity"));
			System.out.println(e.getChild("location").getChildText("locationStadium"));
		}
	}
}
