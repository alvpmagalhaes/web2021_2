package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Empresa;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long>{

	List<Empresa> findAll();
	
	Empresa findById(long id);

	Empresa findByCPF(String CNPJ);
}
