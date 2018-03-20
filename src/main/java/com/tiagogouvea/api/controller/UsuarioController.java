package com.tiagogouvea.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagogouvea.api.dtos.UsuarioDto;
import com.tiagogouvea.api.entities.Usuario;
import com.tiagogouvea.api.responses.Response;
import com.tiagogouvea.api.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@PostMapping
	public ResponseEntity<Response<Usuario>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
		
		Response<Usuario> response = new Response<>();
		
		validarSenhas(usuarioDto, result);
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Usuario usuario = usuarioService.save(usuarioDto);
		
		if (usuario == null) {
			response.getErrors().add("Email de usuário já cadastrado.");
			return ResponseEntity.badRequest().body(response);
		}else {
			response.setData(usuario);
			return ResponseEntity.ok(response);
		}
		
	}


	private void validarSenhas(UsuarioDto usuarioDto, BindingResult result) {
		if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmacaoDeSenha())) {
			result.addError(new ObjectError("UsuarioDto", "As senhas não são iguais."));
		}
	}

	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> atualizar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
		
		Response<Usuario> response = new Response<>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmacaoDeSenha())) {
			response.getErrors().add("As senhas não são iguais.");
			return ResponseEntity.badRequest().body(response);
		}
		
		Usuario usuario = usuarioService.save(usuarioDto);
		
		if (usuario == null) {
			response.getErrors().add("Email de usuário já cadastrado.");
			return ResponseEntity.badRequest().body(response);
		}else {
			response.setData(usuario);
			return ResponseEntity.ok(response);
		}
		
	}	


}
