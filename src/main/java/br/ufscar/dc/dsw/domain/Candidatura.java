package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Candidatura {

    private Profissional profissional;
    private Vaga vaga;
    private Date dataCandidatura;
    private StatusCandidatura status;
    private String arquivo;

    public Candidatura() {
        this.dataCandidatura = new Date();
        this.status = StatusCandidatura.ABERTO;
    }

    public Candidatura(Profissional profissional, Vaga vaga, String arquivo) {
        this();
        this.profissional = profissional;
        this.vaga = vaga;
        this.arquivo = arquivo;
    }

    public Candidatura(Profissional profissional, Vaga vaga, Date dataCandidatura, StatusCandidatura status, String arquivo) {
        this.profissional = profissional;
        this.vaga = vaga;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
        this.arquivo = arquivo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
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
