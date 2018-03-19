package com.tiagogouvea.api.security.services;

import javax.servlet.http.HttpServletRequest;

import com.tiagogouvea.api.security.entities.UsuarioAtual;
import com.tiagogouvea.api.security.request.JwtAutenticacaoRequest;

public interface TokenService {
	
	public UsuarioAtual obterUsuarioComToken(JwtAutenticacaoRequest autenticacaoRequest);

	public UsuarioAtual obterRefreshToken(HttpServletRequest servletRequest);
	
}
