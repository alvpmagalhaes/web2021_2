package br.ufscar.dc.dsw.atividadeaa3.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.atividadeaa3.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.atividadeaa3.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IProfissionalService;

import static br.ufscar.dc.dsw.atividadeaa3.domain.TipoPermissao.ROLE_PROFISSIONAL;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	IProfissionalDAO dao;

	public Profissional salvarRest(Profissional profissional) {
		if (profissional.getRole() == null) {
			profissional.setRole(ROLE_PROFISSIONAL.toString());
		}
		return dao.saveAndFlush(profissional);
	}
	
	public void salvar(Profissional profissional) {
		dao.save(profissional);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Profissional buscarPorId(Long id) {
		return dao.getById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}
	
}