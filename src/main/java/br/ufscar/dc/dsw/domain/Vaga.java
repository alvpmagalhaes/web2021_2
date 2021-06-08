package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Vaga {

	private Long codigo;
	private String cargo;
	private String descricao;
	private String remuneracao;
	private Date dataLimite;
	private Empresa empresa;

	public Vaga() {
	}

	public Vaga(Long codigo, String cargo, String descricao, String remuneracao, Date dataLimite, Empresa empresa) {
		this.codigo = codigo;
		this.cargo = cargo;
		this.descricao = descricao;
		this.remuneracao = remuneracao;
		this.dataLimite = dataLimite;
		this.empresa = empresa;
	}

	public Vaga(String cargo, String descricao, String remuneracao, Date dataLimite, Empresa empresa) {
		this.cargo = cargo;
		this.descricao = descricao;
		this.remuneracao = remuneracao;
		this.dataLimite = dataLimite;
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getRemuneracao() {
		return remuneracao;
	}
	public void setRemuneracao(String remuneracao) {
		this.remuneracao = remuneracao;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}
}

