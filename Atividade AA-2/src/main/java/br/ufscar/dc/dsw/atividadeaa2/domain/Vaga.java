package br.ufscar.dc.dsw.atividadeaa2.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vaga")
public class Vaga extends AbstractEntity<Long>  {

	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String cargo;

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false, unique = false, length = 255)
	private String descricao;

	@NotBlank
	@Column(nullable = false, unique = false)
	private String remuneracao;

	@NotBlank
	@Column(nullable = false, unique = false)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	private Date dataLimite;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

