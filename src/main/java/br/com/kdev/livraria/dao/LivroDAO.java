package br.com.kdev.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.kdev.livraria.modelo.Livro;

@SuppressWarnings("serial")
public class LivroDAO implements Serializable{

	@Inject
	private EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<>(em, Livro.class);
	}
	
	public void adicionar(Livro t) {
		dao.adicionar(t);
	}
	public void atualizar(Livro t) {
		dao.atualizar(t);
	}
	public void remove(Livro t) {
		dao.remove(t);
	}
	public List<Livro> listarTodos() {
		return dao.listarTodos();
	}
	public Livro buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}
	
	
	
}
