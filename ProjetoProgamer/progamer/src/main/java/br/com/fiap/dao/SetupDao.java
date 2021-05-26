package br.com.fiap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import br.com.fiap.model.Setup;
import br.com.fiap.util.EntityManagerFacade;

public class SetupDao {
	
	private EntityManager manager = EntityManagerFacade.getEntityManager();
	
	public void save(Setup setup) {
		
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();

		manager.clear();
	}


	public List<Setup> getAll() {
		String jpql = "SELECT s FROM Setup s";
		TypedQuery<Setup> createQuery = manager.createQuery(jpql, Setup.class);
		return createQuery.getResultList();
	}
	
	public Setup findById(Long id) {
		return manager.find(Setup.class,  id);
	}


	public void update(Setup setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.getTransaction().commit();
		manager.close();
		
	}
	
	public void delete(Setup setup) {
		manager.getTransaction().begin();
		manager.remove(setup);
		manager.getTransaction().commit();
		manager.close();
	}

	
	
}
