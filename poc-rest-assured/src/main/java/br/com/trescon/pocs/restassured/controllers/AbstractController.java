package br.com.trescon.pocs.restassured.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractController {

	EntityManager getEm() {
		return getEm(true);
	}
	
	EntityManager getEm(boolean transacional) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pupoc");
		EntityManager em = emf.createEntityManager();
		if (transacional)
			em.getTransaction().begin();
		return em;
	}
	
	void commit(EntityManager em) {
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			
		}
		em.close();
	}
}
