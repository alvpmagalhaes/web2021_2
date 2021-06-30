package br.ufscar.dc.dsw.atividadeaa3.dao;

import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {

	Empresa findByCnpj(String cnpj);

    Empresa findAllByCidade(String nomeCidade);
}
