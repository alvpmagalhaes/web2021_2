package br.ufscar.dc.dsw.atividadeaa2.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;

public interface IEmpresaService {

	Empresa buscarPorId(Long id);

	List<Empresa> buscarTodos();

	void salvar(Empresa empresa);

	void excluir(Long id);	
}