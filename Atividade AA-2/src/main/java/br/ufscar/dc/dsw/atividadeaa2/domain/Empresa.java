package br.ufscar.dc.dsw.atividadeaa2.domain;

public class Empresa extends Login {

	private String cnpj;
	private String nome;
	private String descricao;
	private String cidade;

	public Empresa(String cnpj) {
		super();
		this.setCnpj(cnpj);
	}
	
	public Empresa(String email, String senha, String cnpj, String nome, String descricao, String cidade) {
		super(email, senha, TipoLogin.EMPRESA);
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setDescricao(descricao);
		this.setCidade(cidade);
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
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

