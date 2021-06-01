package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO extends GenericDAO {
	
	public void insert(Profissional profissional){
        String sql = "INSERT INTO Profissional (cpf, nome, email, senha, telefone, sexo, tipoLogin)";
        sql += " VALUES(?, ?, ?, ?, ?, ?, ?)";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getNome());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getSenha());
            statement.setString(5, profissional.getTelefone());
            statement.setNCharacterStream(6, profissional.getSexo());
            statement.setString(7, profissional.getTipoLogin().name());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from Profissional";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                char sexo = resultSet.getString("sexo");
                String dataDeNascimento = resultSet.getString("dataNascimento");
                Profissional profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, dataDeNascimento);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissional profissional){
        String sql = "Delete FROM Empresa where cpf = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional){
        String sql = "UPDATE Profissional SET nome = ?, email = ?, senha = ?, telefone = ?, sexo = ?, dataNascimento = ? WHERE cpf = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setString(4, profissional.getTelefone());
            statement.setString(5, profissional.getCpf());
            statement.setString(6, profissional.getSexo());
            statement.setString(7, profissional.getDataDeNascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Profissional get(String cpf) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissional where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataNascimento = resultSet.getString("dataNascimento");
                profissional = new Profissional(email, senha, cpf, nome, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    /*
    public List<Profissional> getAllCidade(String cidade) {
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
*/
}
