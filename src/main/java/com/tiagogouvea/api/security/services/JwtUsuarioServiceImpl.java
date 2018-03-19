package com.tiagogouvea.api.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tiagogouvea.api.entities.Usuario;
import com.tiagogouvea.api.security.JwtUsuarioFactory;
import com.tiagogouvea.api.services.UsuarioService;

@Service
public class JwtUsuarioServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.findByEmail(email);

		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Nome de usuário não encontrado", email));
		} else {

			return JwtUsuarioFactory.criarUsuario(usuario);

		}

	}

}
