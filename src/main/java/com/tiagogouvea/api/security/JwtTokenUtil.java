package com.tiagogouvea.api.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

}
