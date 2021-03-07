package br.com.kdev.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@SuppressWarnings("serial")
public class DAO<T> implements Serializable{

	private final Class<T> classe;
	@Inject
	EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	public void adicionar(T t) {
		em.getTransaction().begin();
		em.persist(t);

		em.getTransaction().commit();
		
	}

	public void atualizar(T t) {
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		
	}

	public void remove(T t) {
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		
	}

	public List<T> listarTodos() {
		
		String sql = String.format("select a from %s a", classe.getName());
		TypedQuery<T> query = em.createQuery(sql, classe);
		return query.getResultList();
	}

	public T buscarPorId(Integer id) {

		TypedQuery<T> query = em.createQuery(
				"select a from " + classe.getName() + " a " + "								where a.id=:id ", classe);
		query.setParameter("id", id);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new NoResultException();
		}
	}
}
