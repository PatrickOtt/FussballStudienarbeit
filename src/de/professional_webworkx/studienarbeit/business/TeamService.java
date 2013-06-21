package de.professional_webworkx.studienarbeit.business;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.professional_webworkx.studienarbeit.model.Team;

/**
 * TeamService EJB 
 * @author ottp
 * export2PDF Methode ermöglicht die Ausgabe der 
 * Vereinstabelle als PDF File.
 *
 */
// EJB's Enterprise Java Beans sind im Grunde ganz normale Java-Klassen (POJO's)
@Stateless
public class TeamService implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// da kommt unser Team rein, das wir bearbeiten wollen
	// dazu lassen wir uns noch eben die Getter- / Sette generieren
	

	// per CDI (Contexts and Dependency Injection) lassen wir uns zur Laufzeit
	// vom Glassfish einen EntityManager "injezieren", d.h. der Glassfish kümmert
	// sich um die Erzeugung eines EntityManagers und verwaltet diesen und 
	// "entsorgt" ihn auch wieder, wenn er nicht mehr benötigt wird.
	@PersistenceContext
	private EntityManager em;

	
	// einen Verein anhand der teamID aus der Datenbank lesen
	public Team getTeamById(int teamID) {
		TypedQuery<Team> query = em.createNamedQuery(Team.GET_TEAM_BY_ID, Team.class);
		return query.setParameter("teamID", teamID).getSingleResult();
	}
	
	// Alle Vereine holen
	public List<Team> getAllTeams() {
		
		// vorerst arbeiten wir mit selbstgeschriebenen Queries
		// wir sagen schon vor dem senden der Query an die DB, das wir Team-Objects haben wollen
		TypedQuery<Team> query = em.createNamedQuery(Team.GET_ALL_TEAMS, Team.class);
		// durch den Aufruf von getResultList() wird die Query nun gegen die DB geworfen und 
		// gibt unsere Teams zurück. 
		// Falls nicht zurück kommt, kann hier eine NoResultException auftreten, diese kann 
		// später noch implementiert werden
		return query.getResultList();
	}
	
	
	public Document export2PDF() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfPCell cell;
		Document pdf = new Document();
		try {
			PdfWriter.getInstance(pdf, baos);
			Font defaultFont = new Font(Font.FontFamily.HELVETICA, 10);
			pdf.open();
			pdf.add(new Paragraph("Vereinsübersicht 1. Fussball Bundesliga"));
			PdfPTable pdfPTable = new PdfPTable(new float[] {1f, 4f, 1f, 1f});
			cell = new PdfPCell(new Phrase("Teamname", defaultFont));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(2);
			pdfPTable.addCell(cell);
			
			List<Team> teams = getAllTeams();
			for(Team team : teams) {
				cell = new PdfPCell(new Phrase(team.getTeamName(), defaultFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfPTable.addCell(cell);
			}
			pdf.add(pdfPTable);
			pdf.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		/*FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		*/
		
		return pdf;
	}

	// damit updaten wir das schon vorhandene Team
	// in der Datenbank
	public void saveTeam(Team currentTeam) {
		em.merge(currentTeam);
	}

	// Diese Methode holt uns nun mit Hilfe einer CriteriaQuery das gewünschte Team
	public Team getTeamByCriteria(int teamID) {

		// vom EntityManager lassen wir uns einen CriteriaBuilder geben
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// mit dem Builder können wir uns nur eine CriteriaQuery<T> erzeugen
		CriteriaQuery<Team> cq = cb.createQuery(Team.class);
		
		Root<Team> team = cq.from(Team.class);
		// hiermit definieren wir unserer WHERE-Klausel, was muss übereinstimmen, damit wir das Team
		// geliefert bekommen -> in unserem Fall die teamID, die übergeben wir ja auch
		cq.where(cb.equal(team.get("teamID"), teamID));
		
		// daraus wird eine TypedQuery<Team>
		TypedQuery<Team> query = em.createQuery(cq);
		
		// hier lassen wir uns dann ein Object zurückliefern
		return query.getSingleResult();
	}

}
