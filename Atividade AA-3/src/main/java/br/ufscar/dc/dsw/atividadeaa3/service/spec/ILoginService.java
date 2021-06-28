package br.ufscar.dc.dsw.atividadeaa3.service.spec;

import br.ufscar.dc.dsw.atividadeaa3.domain.Login;

import java.util.List;

public interface ILoginService {

	Login buscarPorId(Long id);

	Login buscarPorUsername(String username);

	List<Login> buscarTodos();

	void salvar(Login login);

	void excluir(Long id);
}
