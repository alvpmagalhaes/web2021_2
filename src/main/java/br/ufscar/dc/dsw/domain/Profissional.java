package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;
import java.util.Date;

public class Profissional extends Login {

	private String email;
	private String cpf;
	private String nome;
	private String telefone;
	private String sexo;
	private Date dataDeNascimento;
	
	public Profissional(String cpf) {
		super();
		this.setCpf(cpf);
	}
	
	public Profissional(String email, String senha, String cpf, String nome, String telefone, String sexo, Date dataDeNascimento) {
		super(email, senha, TipoLogin.PROFISSIONAL);
		this.setCpf(cpf);
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setSexo(sexo);
		this.setDataDeNascimento(dataDeNascimento);
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

