package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Reserva;
import br.com.fiap.util.EntityManagerFacade;


public class ReservaDAO {

	private EntityManager manager = new EntityManagerFacade().getEntityManager();

	public void save(Reserva reserva) {
		manager.getTransaction().begin();
		manager.persist(reserva);
		manager.getTransaction().commit();
		manager.clear();
	}

	public List<Reserva> getAll() {
		String jpql = "SELECT u FROM Reserva u";
		TypedQuery<Reserva> createQuery = manager.createQuery(jpql, Reserva.class);
		return createQuery.getResultList();
	}
	public Reserva findById(int id) {
		return manager.find(Reserva.class, id);		
	}
	
	public void update(Reserva setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void destroy(Reserva reserva) {
        manager.getTransaction().begin();
        manager.remove(reserva);
        manager.flush();
        manager.getTransaction().commit();   
    }
	public boolean exist(Reserva reserva) {
		TypedQuery<Reserva> query = manager.createQuery(
				"SELECT u FROM Reserva u WHERE " + 
		"local=:local" 
		+ "data=:data", 
		 Reserva.class);
		query.setParameter("local", reserva.getLocal());
		query.setParameter("data", reserva.getData());
		

		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}
