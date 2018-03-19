package com.tiagogouvea.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiagogouvea.api.dtos.UsuarioDto;
import com.tiagogouvea.api.entities.Usuario;
import com.tiagogouvea.api.entities.enums.PerfilEnum;
import com.tiagogouvea.api.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder senhaEncoder;
	
	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario save(UsuarioDto usuario) {
		Usuario usurioCreated = usuarioRepository.save(usuarioDtoParaUsuario(usuario));
		usurioCreated.setSenha(null);
		return usurioCreated;
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
	
	
	
	private Usuario usuarioDtoParaUsuario(UsuarioDto usuarioDto) {
		
		Usuario usuario = new Usuario();
		
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setSenha(senhaEncoder.encode(usuarioDto.getSenha()));
		usuario.setPerfil(PerfilEnum.ROLE_CLIENTE);
		
		return usuario;
	}

}
