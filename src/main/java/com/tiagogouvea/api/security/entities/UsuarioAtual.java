package com.tiagogouvea.api.security.entities;

import com.tiagogouvea.api.entities.Usuario;

public class UsuarioAtual {

	private String token;
	private Usuario usuario;
	
	public UsuarioAtual() {
		super();
	}

	public UsuarioAtual(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
