package com.tiagogouvea.api.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogouvea.api.security.entities.UsuarioAtual;
import com.tiagogouvea.api.security.request.JwtAutenticacaoRequest;
import com.tiagogouvea.api.security.services.TokenService;

@RestController
@CrossOrigin(origins = "*")
public class AutenticacaoController {

	@Autowired
	TokenService tokenService;

	@PostMapping(value = "api/auth")
	public ResponseEntity<?> criarTokenDeAutenticacao(@RequestBody JwtAutenticacaoRequest autenticacaoRequest)
			throws Exception {

		UsuarioAtual usuarioAtual = tokenService.obterUsuarioComToken(autenticacaoRequest);

		if (usuarioAtual != null) {
			return ResponseEntity.ok(usuarioAtual);
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping(value = "api/refresh")
	public ResponseEntity<?> criarRefreshToken(HttpServletRequest servletRequest) throws Exception {

		UsuarioAtual usuarioAtual = tokenService.obterRefreshToken(servletRequest);

		if (usuarioAtual != null) {

			return ResponseEntity.ok(usuarioAtual);

		} else {

			return ResponseEntity.badRequest().body(null);

		}

	}

}
