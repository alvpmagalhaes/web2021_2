package br.ufscar.dc.dsw.atividadeaa3.service.impl;

import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.atividadeaa3.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IEmpresaService;


import static br.ufscar.dc.dsw.atividadeaa3.domain.TipoPermissao.ROLE_EMPRESA;


@Service
@Transactional(readOnly = false)
public class EmpresaService implements IEmpresaService {

	@Autowired
	IEmpresaDAO dao;
	
	public Empresa salvarRest(Empresa empresa) {
		if (empresa.getRole() == null) {
			empresa.setRole(ROLE_EMPRESA.toString());
		}
		
		return dao.saveAndFlush(empresa);
	}
	
	public void salvar(Empresa empresa) {
		dao.save(empresa);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public Empresa buscarPorCidade(String nomeCidade) {
		return dao.findAllByCidade(nomeCidade);
	}

	@Transactional
	public Empresa buscarPorId(Long id) {
		return dao.findById(id.longValue()).orElse(null);
	}

	@Override
	public Empresa buscarPorNome(String nome) {
		return null;
	}

	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}
	
}