package br.com.kdev.livraria.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria");

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		System.out.println("Criei o entitymanager");
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager em) {
		System.out.println("fechei o entitymanager");
		em.close();
	}
}
