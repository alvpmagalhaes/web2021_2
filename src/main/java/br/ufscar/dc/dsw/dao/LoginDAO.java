package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Login;
import br.ufscar.dc.dsw.domain.TipoLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends GenericDAO {

    public Login getLoginByEmail(String email) {
        Login login = null;
        StringBuilder sql = new StringBuilder()
                .append("SELECT email,senha,tipoLogin").append(" ")
                .append("FROM Profissional").append(" ")
                .append("WHERE email = ?").append(" ")
                .append("UNION").append(" ")
                .append("SELECT email,senha,tipoLogin").append(" ")
                .append("FROM Empresa").append(" ")
                .append("WHERE email = ?").append(" ");
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            statement.setString(1, email);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String senha = resultSet.getString("senha");
                String tipoLogin = resultSet.getString("tipoLogin");
                login = new Login(email,senha, TipoLogin.valueOf(tipoLogin));
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return login;
    }
}
