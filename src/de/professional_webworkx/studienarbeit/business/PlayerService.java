package de.professional_webworkx.studienarbeit.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.professional_webworkx.studienarbeit.model.Player;

@Stateless(name="PlayerService")
public class PlayerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Player> getAllPlayer() {
		TypedQuery<Player> query = em.createNamedQuery(Player.GET_ALL_PLAYER, Player.class);
		return query.getResultList();
	}

}
