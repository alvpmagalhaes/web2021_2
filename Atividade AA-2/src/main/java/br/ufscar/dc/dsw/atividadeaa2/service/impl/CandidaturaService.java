package br.ufscar.dc.dsw.atividadeaa2.service.impl;

import br.ufscar.dc.dsw.atividadeaa2.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.atividadeaa2.dao.IVagaDAO;
import br.ufscar.dc.dsw.atividadeaa2.domain.Candidatura;
import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa2.domain.Vaga;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class CandidaturaService implements ICandidaturaService {

	@Autowired
	ICandidaturaDAO dao;
	
	public void salvar(Candidatura candidatura) {
		dao.save(candidatura);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Candidatura buscarPorId(Long id) {
		return dao.getById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Candidatura> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Candidatura> buscarPorVaga(Vaga vaga) {
		return dao.findAllByVaga(vaga);
	}

	@Transactional(readOnly = true)
	public List<Candidatura> buscarPorProfissional(Profissional profissional) {
		return dao.findAllByProfissional(profissional);
	}

}
