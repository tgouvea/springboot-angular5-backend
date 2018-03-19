package com.tiagogouvea.api.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Classe responsavel por tratar erro de autenticação
 * @author Tiago Gouvêa
 *
 */
@Component
public class JwtAutenticacaoEntryPoint implements AuthenticationEntryPoint, Serializable{

	private static final long serialVersionUID = -6804010703873383213L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado");
				
	}
	
	

}
