package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Empresa {

	private String email;
	private String senha;
	private String cnpj;
	private String nome;
	private String descricao;
	private String cidade;

	
	public Empresa(String cnpj) {
		this.setCnpj(cnpj);
		
	}
	
	public Empresa(String email, String senha, String cnpj, String nome, String descricao, String cidade) {
		this.setEmail(email);
		this.setSenha(senha);
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setDescricao(descricao);
		this.setCidade(cidade);
		
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cpf) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	

}

