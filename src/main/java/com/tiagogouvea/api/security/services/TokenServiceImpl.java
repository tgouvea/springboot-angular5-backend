package com.tiagogouvea.api.security.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tiagogouvea.api.entities.Usuario;
import com.tiagogouvea.api.security.JwtTokenUtil;
import com.tiagogouvea.api.security.entities.UsuarioAtual;
import com.tiagogouvea.api.security.request.JwtAutenticacaoRequest;
import com.tiagogouvea.api.services.UsuarioService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	public AuthenticationManager authenticationManager;
	@Autowired
	public JwtTokenUtil jwtTokenUtil;
	@Autowired
	public UserDetailsService userDetailsService;
	@Autowired
	public UsuarioService usuarioService;

	@Override
	public UsuarioAtual obterUsuarioComToken(JwtAutenticacaoRequest autenticacaoRequest) {

		// Obtem autenticação atraves do usuario e senha
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(autenticacaoRequest.getEmail(),
						autenticacaoRequest.getSenha()));

		// Define a autenticação no contexto de segurança
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Busca para o contexto do spring
		final UserDetails userDetails = userDetailsService.loadUserByUsername(autenticacaoRequest.getEmail());

		// gera o token para o usuário logado
		final String token = jwtTokenUtil.gerarToken(userDetails);

		// Busca as informações do usuário
		final Usuario usuario = usuarioService.findByEmail(autenticacaoRequest.getEmail());

		// Zera a senha para não ser enviada por engano
		usuario.setSenha(null);

		return new UsuarioAtual(token, usuario);

	}

	public UsuarioAtual obterRefreshToken(HttpServletRequest servletRequest) {

		// Obtem token do header
		String token = servletRequest.getHeader("Authorization");

		// Obtem o nome do usuário do token enviado
		String nomeUsuario = jwtTokenUtil.obterNomeUsuarioDoToken(token);

		// Busca as informações do usuário
		final Usuario usuario = usuarioService.findByEmail(nomeUsuario);
		usuario.setSenha(null);

		if (jwtTokenUtil.TokenPodeSerAtualizado(token)) {
			String refreshToken = jwtTokenUtil.refreshToken(token);
			return new UsuarioAtual(refreshToken, usuario);
		} else {
			return null;
		}

	}

}