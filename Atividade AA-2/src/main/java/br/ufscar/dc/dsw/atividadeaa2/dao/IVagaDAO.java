package br.ufscar.dc.dsw.atividadeaa2.dao;

import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVagaDAO extends JpaRepository<Vaga, Long> {

	List<Vaga> findAllByEmpresa(Empresa empresa);
}
