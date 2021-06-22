package br.ufscar.dc.dsw.atividadeaa2.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Empresa")
public class Empresa extends Login {

	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.empresa.CNPJ}")
	@Column(nullable = false, unique = true, length = 60)
	private String cnpj;

	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

	@Size(max = 255)
	@Column
	private String descricao;

	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String cidade;


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

