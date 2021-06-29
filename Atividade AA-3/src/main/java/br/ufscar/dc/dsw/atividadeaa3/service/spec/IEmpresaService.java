package br.ufscar.dc.dsw.atividadeaa3.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;

public interface IEmpresaService {
	
	public Empresa salvarRest(Empresa empresa);
	
	public Empresa excluirRest(Long id);

	Empresa buscarPorId(Long id);

	List<Empresa> buscarTodos();

	void salvar(Empresa empresa);

	void excluir(Long id);	
}