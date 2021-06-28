package br.ufscar.dc.dsw.atividadeaa2.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;

public interface IProfissionalService {

	Profissional buscarPorId(Long id);

	List<Profissional> buscarTodos();

	void salvar(Profissional profissional);

	void excluir(Long id);	
}