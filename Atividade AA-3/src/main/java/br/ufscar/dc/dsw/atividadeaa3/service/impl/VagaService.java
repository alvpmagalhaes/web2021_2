package br.ufscar.dc.dsw.atividadeaa3.service.impl;

import br.ufscar.dc.dsw.atividadeaa3.dao.IVagaDAO;
import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.domain.Vaga;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class VagaService implements IVagaService {

	@Autowired
	IVagaDAO dao;
	
	public Vaga salvarRest(Vaga vaga) {
		return dao.saveAndFlush(vaga);
	}
	
	public void salvar(Vaga vaga) {
		dao.save(vaga);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	public Vaga buscarPorId(Long id) {
		return dao.findById(id.longValue()).orElse(null);;
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarTodos(String c) {
		return dao.findAllByEmpresaCidade(c);
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarPorEmpresa(Empresa empresa) {
		return dao.findAllByEmpresa(empresa);
	}

	@Override
	public List<Vaga> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public List<Vaga> buscarAllPorId(Long id) {
		return dao.getAllById(id);
	}
	@Override
	public List<Vaga> buscarAllPorIdeData(Long id) {
		return dao.getAllByIdandDate(id);
	}
}
