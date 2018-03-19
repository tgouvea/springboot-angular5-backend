package com.tiagogouvea.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tiagogouvea.api.entities.Usuario;
import com.tiagogouvea.api.entities.enums.PerfilEnum;

public class JwtUsuarioFactory {

	private JwtUsuarioFactory() {
	}

	/**
	 * Método responsável por converter o usuario para o jwtUsuario
	 * @param usuario
	 * @return JwtUsuario
	 */
	public static JwtUsuario criarUsuario(Usuario usuario) {
		return new JwtUsuario(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
				mapearPerfis(usuario.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato esperado pelo spring security
	 * @param perfilEnum
	 * @return perfil no formado do spring security
	 */
	private static List<GrantedAuthority> mapearPerfis(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));

		return authorities;
	}

}
