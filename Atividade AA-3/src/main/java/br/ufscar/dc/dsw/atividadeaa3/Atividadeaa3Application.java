package br.ufscar.dc.dsw.atividadeaa3;

import br.ufscar.dc.dsw.atividadeaa3.dao.*;
import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.domain.Login;
import br.ufscar.dc.dsw.atividadeaa3.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa3.domain.Vaga;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Date;

import static br.ufscar.dc.dsw.atividadeaa3.util.DataUtil.converteLocalDateToDate;

@SpringBootApplication
public class Atividadeaa3Application {

	public static void main(String[] args) {
		SpringApplication.run(Atividadeaa3Application.class, args);
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

			Date data = new Date();

			Vaga v1 = new Vaga();
			v1.setEmpresa(e1);
			v1.setCargo("Estágio");
			v1.setDataLimite(converteLocalDateToDate(LocalDate.now().minusDays(2)));
			v1.setDescricao("Vaga Estágio teste");
			v1.setRemuneracao("1234,00");

			vagaDAO.save(v1);

			Vaga v2 = new Vaga();
			v2.setEmpresa(e1);
			v2.setCargo("Junior");
			v2.setDataLimite(converteLocalDateToDate(LocalDate.now().plusDays(2)));
			v2.setDescricao("Vaga Junior teste");
			v2.setRemuneracao("3224,00");

			vagaDAO.save(v2);

			Vaga v3 = new Vaga();
			v3.setEmpresa(e2);
			v3.setCargo("Estágio empresa b");
			v3.setDataLimite(converteLocalDateToDate(LocalDate.now().plusDays(2)));
			v3.setDescricao("Vaga Estágio teste");
			v3.setRemuneracao("1234,00");

			vagaDAO.save(v3);

			Vaga v4 = new Vaga();
			v4.setEmpresa(e2);
			v4.setCargo("Junior empresa b");
			v4.setDataLimite(converteLocalDateToDate(LocalDate.now().minusDays(2)));
			v4.setDescricao("Vaga Junior teste");
			v4.setRemuneracao("3224,00");

			vagaDAO.save(v4);

		};
	}

}
