package br.com.kdev.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.kdev.livraria.dao.AutorDAO;
import br.com.kdev.livraria.modelo.Autor;
import br.com.kdev.livraria.util.RedirectView;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AutorBean implements Serializable{

	private Autor autor = new Autor();
	private Integer autorId;
	
	@Inject
	private AutorDAO dao;

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return dao.listarTodos();
	}

	public RedirectView gravar() {
		System.out.println(this.autor);
		if (this.autor.getId() == null) {
			dao.adicionar(this.autor);
		} else {
			dao.atualizar(this.autor);
		}
		this.autor = new Autor();

		return new RedirectView("livro");
	}

	public void remover(Autor autor) {
		this.autor = autor;
		System.out.println("Removendo autor" + this.autor);
		dao.remove(this.autor);
	}

	public void alterar(Autor autor) {
		System.out.println("Alterando autor" + autor);
		this.autor = autor;
	}
	
	public void carregarAutorPorId() {
		try {
		this.autor = dao.buscarPorId(autorId);
		if(this.autor == null) {
			this.autor = new Autor();
		}
		}catch(Exception ex) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Deu ruim!!"));
		}
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
