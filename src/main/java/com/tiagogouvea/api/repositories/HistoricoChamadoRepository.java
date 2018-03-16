package com.tiagogouvea.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiagogouvea.api.entities.HistoricoChamado;

public interface HistoricoChamadoRepository extends MongoRepository<HistoricoChamado, String>{

	Iterable<HistoricoChamado> findByChamadoIdOrderByDataMudanca(String chamadoId);
	
}
