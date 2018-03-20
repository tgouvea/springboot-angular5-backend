package com.tiagogouvea.api.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7794723381795755249L;
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiracao;

	public String obterNomeUsuarioDoToken(String token) {

		String nomeUsuario = null;

		try {
			final Claims claims = obterClaimsDoToken(token);
			nomeUsuario = claims.getSubject();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return nomeUsuario;
	}

	public Date obterDataDeExpiracaoDoToken(String token) {

		Date expiracao = null;

		try {
			final Claims claims = obterClaimsDoToken(token);
			expiracao = claims.getExpiration();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return expiracao;
	}

	public Claims obterClaimsDoToken(String token) {

		Claims claims = null;

		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return claims;
	}

	public String gerarToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());

		final Date dataDeCriacao = new Date();
		claims.put(CLAIM_KEY_CREATED, dataDeCriacao);

		return obterToken(claims);

	}

	private String obterToken(Map<String, Object> claims) {

		final Date dataDeCriacao = (Date) claims.get(CLAIM_KEY_CREATED);

		final Date dataExpiracao = new Date(dataDeCriacao.getTime() + expiracao * 1000);

		return Jwts.builder().setClaims(claims).setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public String refreshToken(String token) {

		String refreshToken = null;

		try {
			final Claims claims = obterClaimsDoToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshToken = obterToken(claims);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return refreshToken;

	}

	private Boolean tokenExpirado(String token) {
		final Date expiracao = obterDataDeExpiracaoDoToken(token);
		return expiracao.before(new Date());
	}

	public Boolean TokenPodeSerAtualizado(String token) {
		return (!tokenExpirado(token));
	}
	
	public Boolean validarToken(String token, UserDetails userDetails) {
		
		JwtUsuario jwtUsuario = (JwtUsuario) userDetails;
		
		final String nomeUsuario = obterNomeUsuarioDoToken(token);
		
		
		return nomeUsuario.equals(jwtUsuario.getUsername()) && !tokenExpirado(token);
	}
	
}
