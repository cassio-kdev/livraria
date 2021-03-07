package br.com.kdev.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.kdev.livraria.modelo.Autor;

public class AutorDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em;
	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<>(this.em, Autor.class);
	}

	public void adicionar(Autor t) {
		System.out.println("Criando autor no DAO: " + t);
		dao.adicionar(t);
	}

	public void atualizar(Autor t) {
		System.out.println("Alterando autor no DAO: " + t);
		dao.atualizar(t);
	}

	public void remove(Autor t) {
		System.out.println("Removendo autor no DAO: " + t);
		dao.remove(t);
	}

	public List<Autor> listarTodos() {
		System.out.println("Listando autor no DAO");
		return dao.listarTodos();
	}

	public Autor buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}
}
