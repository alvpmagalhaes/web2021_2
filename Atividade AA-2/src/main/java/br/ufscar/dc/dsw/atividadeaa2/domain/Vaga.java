package br.ufscar.dc.dsw.atividadeaa2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
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

	@Column(nullable = false, unique = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataLimite;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(String remuneracao) {
		this.remuneracao = remuneracao;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}

