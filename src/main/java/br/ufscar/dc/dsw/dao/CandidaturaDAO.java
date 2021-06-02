package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.util.DataUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CandidaturaDAO extends GenericDAO {

    private ProfissionalDAO profissionalDAO;
    private VagaDAO vagaDAO;

    public CandidaturaDAO() {
        super();
        profissionalDAO = new ProfissionalDAO();
        vagaDAO = new VagaDAO();
    }

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

    public List<Candidatura> getAllByCnpjEmpresa(Empresa empresa) {
        List<Candidatura> candidaturas = new ArrayList<>();

        String sql = "SELECT ca.* from Candidatura ca INNER JOIN Vaga v ON ca.codigoVaga = v.codigo WHERE v.cnpjEmpresa = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getCnpj());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Date dataCandidatura = resultSet.getDate("dataCandidatura");
                StatusCandidatura status = StatusCandidatura.valueOf(resultSet.getString("status"));
                String arquivo = resultSet.getString("arquivo");

                Profissional profissional = profissionalDAO.get(resultSet.getString("cpfProfissional"));
                Vaga vaga = vagaDAO.get(resultSet.getLong("codigoVaga"));
                Candidatura candidatura = new Candidatura(profissional,vaga,dataCandidatura,status,arquivo);
                candidaturas.add(candidatura);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return candidaturas;
    }

    public void recusar(String dataCandidatura, String codigoVaga, String cpfProfissional) {
        String sql = "UPDATE Candidatura SET status = ? WHERE dataCandidatura = ? AND codigoVaga = ? AND cpfProfissional = ? ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, StatusCandidatura.NAO_SELECIONADO.name());
            statement.setString(2, dataCandidatura);
            statement.setString(3, codigoVaga);
            statement.setString(4, cpfProfissional);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void aprovar(String dataCandidatura, String codigoVaga, String cpfProfissional) {
        String sql = "UPDATE Candidatura SET status = ? WHERE dataCandidatura = ? AND codigoVaga = ? AND cpfProfissional = ? ";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, StatusCandidatura.ENTREVISTA.name());
            statement.setString(2, dataCandidatura);
            statement.setString(3, codigoVaga);
            statement.setString(4, cpfProfissional);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
