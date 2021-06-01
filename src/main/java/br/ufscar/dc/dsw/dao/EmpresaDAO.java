package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO extends GenericDAO {

    public void insert(Empresa empresa){
        String sql = "INSERT INTO Empresa (cnpj, nome, email, senha, cidade, descricao, tipoLogin)";
        sql += " VALUES(?, ?, ?, ?, ?, ?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getNome());
            statement.setString(3, empresa.getEmail());
            statement.setString(4, empresa.getSenha());
            statement.setString(5, empresa.getCidade());
            statement.setString(6, empresa.getDescricao());
            statement.setString(7, empresa.getTipoLogin().name());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Empresa> getAll() {

        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * from Empresa";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                String descricao = resultSet.getString("descricao");
                Empresa empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
                listaEmpresas.add(empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }

    public void delete(Empresa empresa){
        String sql = "Delete FROM Empresa where cnpj = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Empresa empresa){
        String sql = "UPDATE Empresa SET nome = ?, email = ?, senha = ?, cidade = ?, descricao = ? WHERE cnpj = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getEmail());
            statement.setString(3, empresa.getSenha());
            statement.setString(4, empresa.getCidade());
            statement.setString(5, empresa.getCnpj());
            statement.setString(6, empresa.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Empresa get(String cnpj) {
        Empresa empresa = null;

        String sql = "SELECT * from Empresa where cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                String descricao = resultSet.getString("descricao");
                empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }

    public List<Empresa> getAllCidade(String cidade) {
        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * from Empresa where cidade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String descricao = resultSet.getString("descricao");
                Empresa empresa = new Empresa(email, senha, cnpj, nome, descricao, cidade);
                listaEmpresas.add(empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }
}

