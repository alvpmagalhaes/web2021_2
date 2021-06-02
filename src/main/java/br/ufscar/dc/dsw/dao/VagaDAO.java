package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.DataUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VagaDAO  extends GenericDAO {

    private EmpresaDAO empresaDao;

    public VagaDAO() {
        super();
        empresaDao = new EmpresaDAO();
    }

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
            statement.setString(4, DataUtil.convertDateToString(vaga.getDataLimite()));
            statement.setString(5, vaga.getEmpresa().getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {
        List<Vaga> vagas = new ArrayList<>();

        String sql = "SELECT * from Vaga";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Long codigo = resultSet.getLong("codigo");
                String cargo = resultSet.getString("cargo");
                String descricao = resultSet.getString("descricao");
                String remuneracao = resultSet.getString("remuneracao");
                Date dataLimite = resultSet.getDate("dataLimite");
                Empresa empresa = empresaDao.get(resultSet.getString("cnpjEmpresa"));
                Vaga vaga = new Vaga(codigo,cargo,descricao,remuneracao,dataLimite,empresa);
                vagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vagas;
    }

    public List<Vaga> getAllByCidade(String cidade) {
        List<Vaga> vagas = new ArrayList<>();

        String sql = "SELECT v.* from Vaga v INNER JOIN Empresa e ON v.cnpjEmpresa = e.cnpj WHERE e.cidadade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long codigo = resultSet.getLong("codigo");
                String cargo = resultSet.getString("cargo");
                String descricao = resultSet.getString("descricao");
                String remuneracao = resultSet.getString("remuneracao");
                Date dataLimite = resultSet.getDate("dataLimite");
                Empresa empresa = empresaDao.get(resultSet.getString("cnpjEmpresa"));
                Vaga vaga = new Vaga(codigo,cargo,descricao,remuneracao,dataLimite,empresa);
                vagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vagas;
    }

    public List<Vaga> getAllByCnpjEmpresa(String cnpjEmpresa) {
        List<Vaga> vagas = new ArrayList<>();

        String sql = "SELECT v.* from Vaga v WHERE v.cnpjEmpresa = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpjEmpresa);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long codigo = resultSet.getLong("codigo");
                String cargo = resultSet.getString("cargo");
                String descricao = resultSet.getString("descricao");
                String remuneracao = resultSet.getString("remuneracao");
                Date dataLimite = resultSet.getDate("dataLimite");
                Empresa empresa = empresaDao.get(resultSet.getString("cnpjEmpresa"));
                Vaga vaga = new Vaga(codigo,cargo,descricao,remuneracao,dataLimite,empresa);
                vagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vagas;
    }

    public Vaga get(Long codigo) {
        Vaga vaga = null;

        String sql = "SELECT * from Vaga where codigo = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cargo = resultSet.getString("cargo");
                String descricao = resultSet.getString("descricao");
                String remuneracao = resultSet.getString("remuneracao");
                Date dataLimite = resultSet.getDate("dataLimite");
                Empresa empresa = empresaDao.get(resultSet.getString("cnpjEmpresa"));
                vaga = new Vaga(codigo,cargo,descricao,remuneracao,dataLimite,empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }
}
