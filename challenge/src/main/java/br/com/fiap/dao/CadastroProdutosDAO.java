package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.model.CadastroProdutos;
import br.com.fiap.util.EntityManagerFacade;

public class CadastroProdutosDAO {
	

	private EntityManager manager = new EntityManagerFacade().getEntityManager();

	public void save(CadastroProdutos cadastroProdutos) {
		manager.getTransaction().begin();
		manager.persist(cadastroProdutos);
		manager.getTransaction().commit();
		manager.clear();
	}
	
	public List<CadastroProdutos> getAll() {
		String jpql = "SELECT u FROM CadastroProdutos u";
		TypedQuery<CadastroProdutos> createQuery = manager.createQuery(jpql, CadastroProdutos.class);
		return createQuery.getResultList();
	}
	public CadastroProdutos findById(int id) {
		return manager.find(CadastroProdutos.class, id);		
	}
	
	public void update(CadastroProdutos cadastroProdutos) {
		manager.getTransaction().begin();
		manager.merge(cadastroProdutos);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void destroy(CadastroProdutos cadastroProdutos) {
        manager.getTransaction().begin();
        manager.remove(cadastroProdutos);
        manager.flush();
        manager.getTransaction().commit();   
    }
	public boolean exist(CadastroProdutos cadastroProdutos) {
		TypedQuery<CadastroProdutos> query = manager.createQuery(
				"SELECT u FROM CadastroProdutos u WHERE " + 
		"local=:local" 
		+ "description=:description", 
		 CadastroProdutos.class);
		query.setParameter("local", cadastroProdutos.getLocal());
		query.setParameter("description", cadastroProdutos.getDescription());
		

		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}