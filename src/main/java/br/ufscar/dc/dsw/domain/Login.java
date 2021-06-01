package br.ufscar.dc.dsw.domain;

public class Login {

    private String email;
    private String senha;
    private TipoLogin tipoLogin;

    public Login() {
    }

    public Login(String email, String senha, TipoLogin tipoLogin) {
        this.email = email;
        this.senha = senha;
        this.tipoLogin = tipoLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoLogin getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(TipoLogin tipoLogin) {
        this.tipoLogin = tipoLogin;
    }
}
