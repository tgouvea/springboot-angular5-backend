package com.tiagogouvea.api.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tiagogouvea.api.entities.Chamado;
import com.tiagogouvea.api.entities.HistoricoChamado;
import com.tiagogouvea.api.repositories.ChamadoRepository;
import com.tiagogouvea.api.repositories.HistoricoChamadoRepository;

public class ChamadoServiceImpl implements ChamadoService{
	
	@Autowired
	ChamadoRepository chamadoRepository;
	
	@Autowired
	HistoricoChamadoRepository historicoRepository;

	@Override
	public Chamado salvar(Chamado chamado) {
		return this.chamadoRepository.save(chamado);
	}

	@Override
	public Chamado buscarPorId(String id) {
		return this.chamadoRepository.findOne(id);
	}

	@Override
	public void deletar(String id) {
		this.chamadoRepository.delete(id);
	}

	@Override
	public Page<Chamado> listarChamados(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.chamadoRepository.findAll(pages);
	}

	@Override
	public HistoricoChamado salvarHistorico(HistoricoChamado historicoChamado) {
		return this.historicoRepository.save(historicoChamado);
	}

	@Override
	public Iterable<HistoricoChamado> listarHistoricoChamado(String chamadoId) {
		return this.historicoRepository.findByChamadoIdOrderByDataMudanca(chamadoId);
	}

	@Override
	public Page<Chamado> buscarChamadosPorUsuario(int page, int count, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.chamadoRepository.findByUsuarioIdOrderByData(pages, userId);
	}

	@Override
	public Page<Chamado> buscarPorParametros(int page, int count, String titulo, String status, String prioridade) {
		Pageable pages = new PageRequest(page, count);
		return this.chamadoRepository.findByTituloIgnoreCaseContainingAndStatusAndPrioridadeOrderByData(titulo, status, prioridade, pages);
	}

	@Override
	public Page<Chamado> buscarPorParametrosUsuario(int page, int count, String titulo, String status,
			String prioridade, String userId) {
		Pageable pageable = new PageRequest(page, count);
		return this.chamadoRepository.findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndUsuarioIdOrderByData(titulo, status, prioridade, pageable);
	}

	@Override
	public Page<Chamado> buscarPorNumero(int page, int count, Integer numero) {
		Pageable pageable = new PageRequest(page, count);
		return this.chamadoRepository.findByNumero(numero, pageable);
	}

	@Override
	public Iterable<Chamado> buscarTodos() {
		return this.chamadoRepository.findAll();
	}

	@Override
	public Page<Chamado> buscarPorParametrosTecnico(int page, int count, String titulo, String status,
			String prioridade, String tecnico) {
		Pageable pageable = new PageRequest(page, count);

		return this.chamadoRepository.findByTituloIgnoreCaseContainingAndStatusAndPrioridadeAndTecnicoIdOrderByData(titulo, status, prioridade, pageable);
	}

}
