package br.com.kdev.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.ValidationException;

import br.com.kdev.livraria.dao.AutorDAO;
import br.com.kdev.livraria.dao.LivroDAO;
import br.com.kdev.livraria.modelo.Autor;
import br.com.kdev.livraria.modelo.Livro;
import br.com.kdev.livraria.util.RedirectView;

@Named
@ViewScoped
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	
	@Inject
	private LivroDAO livroDAO;
	
	@Inject
	private AutorDAO autorDAO;
	
	public void carregaLivro() {
		System.out.println("Consultando livro por id: " + this.livroId);
		this.livro = livroDAO.buscarPorId(this.livroId);
	}

	public RedirectView formAutor() {
		return new RedirectView("autor");
	}

	public List<Livro> getLivros() {
		return livroDAO.listarTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void adicionarAutor() {
		if (this.livro.getAutores().contains(new Autor(this.autorId))) {
			throw new RuntimeException("Autor já foi adicinonado");
		}
		Autor autor = autorDAO.buscarPorId(this.autorId);
		this.livro.getAutores().add(autor);
		System.out.println("Total de autores: " + this.livro.getAutores().size());
	}

	public List<Autor> getAutores() {
		return autorDAO.listarTodos();
	}

	public void gravar() {
		System.out.println("Titulo: " + this.livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
//			throw new RuntimeException("Livro deve ter pelo menos um autor!");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um autor!"));
			return;
		}

		if (this.livro.getId() == null) {
			livroDAO.adicionar(this.livro);
		} else {
			livroDAO.atualizar(this.livro);

		}
		this.livro = new Livro();
	}

	public void deveriComecarComUm(FacesContext fc, UIComponent ui, Object value) throws ValidationException {
		String valor = value.toString();
		System.out.println("metodo deveriComecarComUm" + valor);
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISB deve começar com o valor 1"));
		}
	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		livroDAO.remove(livro);
	}

	public void removeAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer id) {
		this.autorId = id;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void carregaDados(Livro livro) {
		this.livro = livro;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

}
