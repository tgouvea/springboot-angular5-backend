package com.tiagogouvea.api.security.request;

import java.io.Serializable;

public class JwtAutenticacaoRequest implements Serializable{

	private static final long serialVersionUID = -7748808965044646280L;
	
	private String email;
	private String senha;
	
	public JwtAutenticacaoRequest() {
		super();
	}

	public JwtAutenticacaoRequest(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
