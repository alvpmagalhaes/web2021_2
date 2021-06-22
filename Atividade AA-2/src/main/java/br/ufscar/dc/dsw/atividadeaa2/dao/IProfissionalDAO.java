package br.ufscar.dc.dsw.atividadeaa2.dao;

import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfissionalDAO extends JpaRepository<Profissional, Long> {

	Profissional findByCpf(String CPF);
}
