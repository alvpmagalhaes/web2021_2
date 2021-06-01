package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Profissional extends Login {

	private String cpf;
	private String nome;
	private String telefone;
	private char sexo;
	private LocalDate dataDeNascimento;
	
	public Profissional(String cpf) {
		super();
		this.setCpf(cpf);
	}
	
	public Profissional(String email, String senha, String cpf, String nome, String telefone, char sexo, LocalDate dataDeNascimento) {
		super(email, senha, TipoLogin.PROFISSIONAL);
		this.setCpf(cpf);
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setSexo(sexo);
		this.setDataDeNascimento(dataDeNascimento);
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
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

}

