package br.com.kdev.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.kdev.livraria.dao.UsuarioDAO;
import br.com.kdev.livraria.modelo.Usuario;
import br.com.kdev.livraria.util.JPAUtil;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDAO dao;
	
	public String logar() {
		System.out.println(this.usuario);
		boolean existe = dao.isValidaUsuario(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		if(existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Login ou senha inválido"));
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		return "livro?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
