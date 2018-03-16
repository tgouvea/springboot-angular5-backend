package com.tiagogouvea.api.services;

import org.springframework.data.domain.Page;

import com.tiagogouvea.api.entities.Usuario;

public interface UsuarioService {

	Usuario findByEmail(String email);
	
	Usuario save(Usuario usuario);
	
	Usuario findById(String id);
	
	void delete(String id);
	
	Page<Usuario> findAll(int page, int count);
	
}
