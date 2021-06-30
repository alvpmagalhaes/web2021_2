package br.ufscar.dc.dsw.atividadeaa3.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.atividadeaa3.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IEmpresaService;

@Service
@Transactional(readOnly = false)
public class EmpresaService implements IEmpresaService {

	@Autowired
	IEmpresaDAO dao;
	
	public Empresa salvarRest(Empresa empresa) {
		return dao.saveAndFlush(empresa);
	}
	

	//deletar empresas por id por rest
	public Empresa excluirRest(Long id) {
		return dao.deleteByIdAndFlush(id);
	}
	
	public void salvar(Empresa empresa) {
		dao.save(empresa);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return dao.getById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}
	
}