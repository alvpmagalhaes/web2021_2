package br.ufscar.dc.dsw.atividadeaa2.service.spec;

import br.ufscar.dc.dsw.atividadeaa2.domain.Candidatura;
import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa2.domain.Vaga;

import java.util.List;

public interface ICandidaturaService {

	Candidatura buscarPorId(Long id);

	List<Candidatura> buscarTodos();

	List<Candidatura> buscarPorProfissional(Profissional profissional);

	List<Candidatura> buscarPorVaga(Vaga vaga);

	List<Candidatura> buscarPorEmpresa(Empresa empresa);

	void salvar(Candidatura candidatura);

	void excluir(Long id);
}
