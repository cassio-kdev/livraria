package br.com.kdev.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.kdev.livraria.modelo.Usuario;

public class UsuarioDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager em;
	
	public boolean isValidaUsuario(Usuario usuario) {
		
		TypedQuery<Usuario> query = em.createQuery(" select u from Usuario u where u.email = :pEmail and u.senha = :pSenha",Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario retorno = query.getSingleResult();
		}catch(NoResultException ex) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
