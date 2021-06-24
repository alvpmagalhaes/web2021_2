package br.ufscar.dc.dsw;

import java.io.File;

import javax.mail.internet.InternetAddress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EmailService service) {
		return (args) -> {

            InternetAddress from = new InternetAddress("<username>@gmail.com", "Fulano");
		    InternetAddress to = new InternetAddress("<email>@<dominio>", "Beltrano");
					
			String subject1 = "Candidatura a vaga";
			String subject2 = "Candidatura a vaga)";

			String body1 = "Olá candidato, você foi aprovado para a próxima etapa, gostariamos de agendar uma reunião para o dia 22/07 às 15h neste link: ";
			String body2 = "Olá candidato, você não foi aprovado para a próxima etapa, mas daqui 2 meses você poderá tentar novamente. Aguardamos você.";
			// Envio sem anexo
			service.send(from, to, subject1, body1);

			// Envio com anexo
			service.send(from, to, subject2, body2, new File("SIGA.pdf"));
		};
	}
}