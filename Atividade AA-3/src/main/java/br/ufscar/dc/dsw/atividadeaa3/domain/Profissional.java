package br.ufscar.dc.dsw.atividadeaa3.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Builder
@Table(name = "Profissional")
public class Profissional extends Login {

	@NotBlank
	@Size(min = 14, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@NotBlank
	@Size(min = 11, max = 15)
	@Column(nullable = false, unique = false, length = 60)
	private String telefone;

	@NotBlank
	@Size(min = 1, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String sexo;

	@NotNull
	@Column(nullable = false, unique = false, length = 60)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dataDeNascimento;

	public Profissional(Login login) {
		this.setId(login.getId());
	}

	public Profissional() {
	}

	public Profissional(@NotBlank @Size(min = 14, max = 14) String cpf, @NotBlank @Size(min = 3, max = 60) String nome, @NotBlank @Size(min = 11, max = 15) String telefone, @NotBlank @Size(min = 1, max = 60) String sexo, @NotNull Date dataDeNascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
}

