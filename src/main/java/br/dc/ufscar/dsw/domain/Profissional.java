package br.dc.ufscar.dsw.domain;

import java.time.LocalDate;

public class Profissional {

	private String email;
	private String senha;
	private String cpf;
	private String nome;
	private String telefone;
	private char sexo;
	private LocalDate dataDeNascimento;
	
	public Profissional(String cpf) {
		this.setCpf(cpf);
		
	}
	
	public Profissional(String email, String senha, String cpf, String nome, String telefone, char sexo, LocalDate dataDeNascimento) {
		this.setEmail(email);
		this.setSenha(senha);
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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

