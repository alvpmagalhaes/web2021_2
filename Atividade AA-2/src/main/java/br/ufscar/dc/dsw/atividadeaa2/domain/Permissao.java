package br.ufscar.dc.dsw.atividadeaa2.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permissao {

    @Id
    private TipoPermissao nome;

    @Enumerated(EnumType.STRING)
    public TipoPermissao getNome() {
        return nome;
    }

    public void setNome(TipoPermissao nome) {
        this.nome = nome;
    }
}
