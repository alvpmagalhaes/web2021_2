package br.ufscar.dc.dsw.atividadeaa3.dao;

import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IVagaDAO extends JpaRepository<Vaga, Long> {

	List<Vaga> findAllByEmpresa(Empresa empresa);

    List<Vaga> findAllByEmpresaCidade(String c);

    @Query("select v from Vaga v where v.id = :id ")
    List<Vaga> getAllById(@PathVariable("id") Long id);
}
