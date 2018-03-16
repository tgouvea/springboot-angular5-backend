package com.tiagogouvea.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiagogouvea.api.entities.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

	Usuario findByEmail(String email);
	
}
