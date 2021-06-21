package br.ufscar.dc.dsw.atividadeaa2.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.atividadeaa2.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	IProfissionalDAO dao;
	
	public void salvar(Profissional profissional) {
		dao.save(profissional);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Profissional buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}
	
}