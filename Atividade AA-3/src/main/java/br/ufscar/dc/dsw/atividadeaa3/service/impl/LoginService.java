package br.ufscar.dc.dsw.atividadeaa3.service.impl;

import br.ufscar.dc.dsw.atividadeaa3.dao.ILoginDAO;
import br.ufscar.dc.dsw.atividadeaa3.domain.Login;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class LoginService implements ILoginService {

	@Autowired
	ILoginDAO dao;
	
	public void salvar(Login login) {
		dao.save(login);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Login buscarPorId(Long id) {
		return dao.getById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Login> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Login buscarPorUsername(String username) {
		return dao.getByUsername(username);
	}
}
