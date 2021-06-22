package br.ufscar.dc.dsw.atividadeaa2.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Candidatura")
public class Candidatura extends AbstractEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @NotBlank
    @Column(nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private Date dataCandidatura;

    @NotBlank
    @Column(nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private StatusCandidatura status;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] arquivo;

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }
}
