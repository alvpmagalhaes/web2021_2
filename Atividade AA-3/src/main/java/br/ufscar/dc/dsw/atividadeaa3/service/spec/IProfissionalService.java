package br.ufscar.dc.dsw.atividadeaa3.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.atividadeaa3.domain.Profissional;

public interface IProfissionalService {

	public Profissional salvarRest(Profissional profissional);

	Profissional buscarPorId(Long id);

	List<Profissional> buscarTodos();

	void salvar(Profissional profissional);

	void excluir(Long id);	
}