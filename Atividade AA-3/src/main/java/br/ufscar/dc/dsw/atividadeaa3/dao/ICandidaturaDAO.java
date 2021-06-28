package br.ufscar.dc.dsw.atividadeaa2.dao;

import br.ufscar.dc.dsw.atividadeaa2.domain.Candidatura;
import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa2.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICandidaturaDAO extends JpaRepository<Candidatura, Long> {

	List<Candidatura> findAllByVaga(Vaga vaga);
	List<Candidatura> findAllByProfissional(Profissional profissional);
    List<Candidatura> findAllByVagaEmpresa(Empresa empresa);
}
