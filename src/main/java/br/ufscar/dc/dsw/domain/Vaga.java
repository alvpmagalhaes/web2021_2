package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Vaga {
	
	private String cargo;
	private String descricao;
	private String remuneracao;
	private String dataLimite;


	
	public Vaga(String cargo, String remuneracao, String dataLimite, String descricao) {
		this.setCargo(cargo);
		this.setRemuneracao(remuneracao);
		this.setDataLimite(dataLimite);
		this.setDescricao(descricao);

		
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
	
	public String getDataLimite() {
		return dataLimite;
	}
	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}

