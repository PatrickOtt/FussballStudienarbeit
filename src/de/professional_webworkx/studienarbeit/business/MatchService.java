package de.professional_webworkx.studienarbeit.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.professional_webworkx.studienarbeit.model.Match;
@Named
@Stateless
public class MatchService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Match> getAllMatches() {
		TypedQuery<Match> query = em.createNamedQuery(Match.GET_ALL_MATCHES, Match.class);
		return query.getResultList();
	}

}
