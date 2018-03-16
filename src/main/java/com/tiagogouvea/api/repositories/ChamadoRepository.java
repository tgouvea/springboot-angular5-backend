package com.tiagogouvea.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiagogouvea.api.entities.Chamado;

public interface ChamadoRepository extends MongoRepository<Chamado, String>{

	Page<Chamado> findByUsuarioIdOrderByData(Pageable pageable, String usuarioId);
	
	Page<Chamado> findByTituloIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingOrderByData(String titulo, String status, String prioridade, Pageable pageable);

	Page<Chamado> findByTituloIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioIdOrderByData(String titulo, String status, String prioridade, Pageable pageable);

	Page<Chamado> findByTituloIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndTecnicoIdOrderByData(String titulo, String status, String prioridade, Pageable pageable);

	Page<Chamado> findByNumero(Integer numero, Pageable pageable);
}
