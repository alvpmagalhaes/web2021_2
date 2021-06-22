package br.ufscar.dc.dsw.atividadeaa2.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
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
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFormat.ISO.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dataDeNascimento;

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

