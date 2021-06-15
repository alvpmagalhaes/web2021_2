package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long>{

	List<Profissional> findAll();
	
	Profissional findById(long id);

	Profissional findByCPF(String CPF);
}
