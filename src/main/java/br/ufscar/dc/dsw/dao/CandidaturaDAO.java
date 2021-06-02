package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.util.DataUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class CandidaturaDAO extends GenericDAO {

    public void insert(Candidatura candidatura){
        String sql = "INSERT INTO Candidatura (dataCandidatura, cpfProfissional, codigoVaga, status, arquivo)";
        sql += " VALUES(?, ?, ?, ?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, DataUtil.convertDateToString(candidatura.getDataCandidatura()));
            statement.setString(2, candidatura.getProfissional().getCpf());
            statement.setLong(3, candidatura.getVaga().getCodigo());
            statement.setString(4, candidatura.getStatus().name());
            statement.setString(5, candidatura.getArquivo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException | ParseException e){
            throw new RuntimeException(e);
        }
    }
}
