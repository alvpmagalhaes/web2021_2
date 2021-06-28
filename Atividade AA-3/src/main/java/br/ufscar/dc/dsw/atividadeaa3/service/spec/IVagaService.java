package br.ufscar.dc.dsw.atividadeaa3.service.spec;

import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.domain.Vaga;

import java.util.List;

public interface IVagaService {

	Vaga buscarPorId(Long id);

	List<Vaga> buscarAllPorId(Long id);

	List<Vaga> buscarTodos(String c);

	List<Vaga> buscarTodos();

	List<Vaga> buscarPorEmpresa(Empresa empresa);

	void salvar(Vaga vaga);

	void excluir(Long id);
}
