package br.ufscar.dc.dsw.atividadeaa3.dao;

import br.ufscar.dc.dsw.atividadeaa3.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ILoginDAO extends JpaRepository<Login, Long> {

	@Transactional
    Login getByUsername(String username);
}
