package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Candidatura {

    private Profissional profissional;
    private Vaga vaga;
    private Date dataCandidatura;
    private Boolean status;

    public Candidatura() {
    }

    public Candidatura(Profissional profissional, Vaga vaga, Date dataCandidatura, Boolean status) {
        this.profissional = profissional;
        this.vaga = vaga;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
