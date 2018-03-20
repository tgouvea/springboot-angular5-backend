package com.tiagogouvea.api.security.services;

import org.springframework.data.domain.Page;

import com.tiagogouvea.api.entities.Chamado;
import com.tiagogouvea.api.entities.HistoricoChamado;

public interface ChamadoService {
	
	Chamado salvar(Chamado chamado);
	
	Chamado buscarPorId(String id);
	
	void deletar(String id);

	Page<Chamado> listarChamados(int page, int count);
	
	HistoricoChamado salvarHistorico(HistoricoChamado historicoChamado);
	
	Iterable<HistoricoChamado> listarHistoricoChamado(String chamadoId);
	
	Page<Chamado> buscarChamadosPorUsuario(int page, int count, String userId);
	
	Page<Chamado> buscarPorParametros(int page, int count, String titulo, String status, String prioridade);
	
	Page<Chamado> buscarPorParametrosUsuario(int page, int count, String titulo, String status, String prioridade, String userId);
	
	Page<Chamado> buscarPorNumero(int page, int count, Integer numero);
	
	Iterable<Chamado> buscarTodos();
	
	Page<Chamado> buscarPorParametrosTecnico(int page, int count, String titulo, String status, String prioridade, String tecnico);
	

	

}
