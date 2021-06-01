package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VagaDAO  extends GenericDAO {

    public void insert(Vaga vaga){
        String sql = "INSERT INTO Vaga (cargo, descricao, remuneracao, dataLimite, cnpjEmpresa)";
        sql += " VALUES(?, ?, ?, ?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, vaga.getCargo());
            statement.setString(2, vaga.getDescricao());
            statement.setString(3, vaga.getRemuneracao());
            //TODO format date
            statement.setString(4, vaga.getDataLimite().toString());
            statement.setString(5, vaga.getEmpresa().getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
