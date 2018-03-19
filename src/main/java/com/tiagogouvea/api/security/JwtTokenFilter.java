package com.tiagogouvea.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Classe repsonsavel por filtrar o token e identificar se ele é valido ou não.
 * @author oi404118
 *
 */
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService UsuarioDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//Obtem o header de autorização
		String token = request.getHeader("Authorization");
		
		//Recupera o nome de usuário do token
		String nomeUsuario = jwtTokenUtil.obterNomeUsuarioDoToken(token);
		
		if (nomeUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			//Recupera informações do usuário através do nome do usuário
			UserDetails userDetails = this.UsuarioDetailsService.loadUserByUsername(nomeUsuario);	
		
			//Valida o token
			if (jwtTokenUtil.validarToken(token, userDetails)) {
				
				//Se token valido define as informações de autorização do usuário no contexto de segurança do spring 				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				logger.info("Usuário autenticado " + nomeUsuario + ", definindo contexto de segurança");
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
