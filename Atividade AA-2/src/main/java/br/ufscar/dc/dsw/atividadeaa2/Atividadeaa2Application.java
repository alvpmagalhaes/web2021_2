package br.ufscar.dc.dsw.atividadeaa2;

import br.ufscar.dc.dsw.atividadeaa2.dao.*;
import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.Login;
import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Atividadeaa2Application {

	public static void main(String[] args) {
		SpringApplication.run(Atividadeaa2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ILoginDAO loginDAO, IProfissionalDAO profissionalDAO, IEmpresaDAO empresaDAO, IVagaDAO vagaDAO, ICandidaturaDAO candidaturaDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {

			Login login = new Login();
			login.setUsername("admin");
			login.setPassword(encoder.encode("admin"));
			login.setRole("ROLE_ADMIN");
			login.setEnabled(true);
			loginDAO.save(login);

			Profissional p1 = new Profissional();
			p1.setUsername("alice@gmail.com");
			p1.setPassword(encoder.encode("alice"));
			p1.setRole("ROLE_PROFISSIONAL");
			p1.setEnabled(true);
			p1.setCpf("111.222.333-44");
			p1.setNome("Alice");
			p1.setTelefone("00 9 0000-0000");
			p1.setSexo("F");
			p1.setDataDeNascimento(new Date("01/01/1970"));
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional();
			p2.setUsername("bob@gmail.com");
			p2.setPassword(encoder.encode("bob"));
			p2.setRole("ROLE_PROFISSIONAL");
			p2.setEnabled(true);
			p2.setCpf("111.222.333-55");
			p2.setNome("Bob");
			p2.setTelefone("00 9 0000-0000");
			p2.setSexo("M");
			p2.setDataDeNascimento(new Date("01/01/1970"));
			profissionalDAO.save(p2);

			Empresa e1 = new Empresa();
			e1.setUsername("empresa_a@gmail.com");
			e1.setPassword(encoder.encode("empresa_a"));
			e1.setRole("ROLE_EMPRESA");
			e1.setEnabled(true);
			e1.setCnpj("11.222.333/4444-55");
			e1.setNome("Empresa A");
			e1.setCidade("São Carlos");
			empresaDAO.save(e1);

			Empresa e2 = new Empresa();
			e2.setUsername("empresa_b@gmail.com");
			e2.setPassword(encoder.encode("empresa_b"));
			e2.setRole("ROLE_EMPRESA");
			e2.setEnabled(true);
			e2.setCnpj("11.222.333/4444-66");
			e2.setNome("Empresa B");
			e2.setCidade("Matão");
			empresaDAO.save(e2);

		};
	}

}
