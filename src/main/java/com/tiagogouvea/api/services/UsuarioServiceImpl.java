package com.tiagogouvea.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tiagogouvea.api.entities.Usuario;
import com.tiagogouvea.api.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findById(String id) {
		return usuarioRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		
		usuarioRepository.delete(id);
		
	}

	@Override
	public Page<Usuario> findAll(int page, int count) {
		
		Pageable pageable = new PageRequest(page, count);
		
		return usuarioRepository.findAll(pageable);
	}

}
