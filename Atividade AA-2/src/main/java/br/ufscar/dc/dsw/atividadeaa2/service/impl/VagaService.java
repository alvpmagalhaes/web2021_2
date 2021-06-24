package br.ufscar.dc.dsw.atividadeaa2.service.impl;

import br.ufscar.dc.dsw.atividadeaa2.dao.IVagaDAO;
import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.Vaga;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class VagaService implements IVagaService {

	@Autowired
	IVagaDAO dao;
	
	public void salvar(Vaga vaga) {
		dao.save(vaga);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	public Vaga buscarPorId(Long id) {
		return dao.getById(id.longValue());
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
}
