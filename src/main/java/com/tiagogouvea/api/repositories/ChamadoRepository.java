package com.tiagogouvea.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiagogouvea.api.entities.Chamado;

public interface ChamadoRepository extends MongoRepository<Chamado, String>{

	Page<Chamado> findByUsuarioIdOrderByData(Pageable pageable, String usuarioId);
	
	Page<Chamado> findByTituloIgnoreCaseContainingAndStatusAndPrioridadeOrderByData(String titulo, String status, String prioridade, Pageable pageable);

	Page<Chamado> findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndUsuarioIdOrderByData(String titulo, String status, String prioridade, Pageable pageable);

	Page<Chamado> findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndTecnicoIdOrderByData(String titulo, String status, String prioridade, Pageable pageable);

	Page<Chamado> findByNumero(Integer numero, Pageable pageable);
}
