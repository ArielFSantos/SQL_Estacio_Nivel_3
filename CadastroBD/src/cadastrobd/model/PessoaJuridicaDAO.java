/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;


import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private final ConectorBD conector;
    private final SequenceManager sequenceManager;

    public PessoaJuridicaDAO(ConectorBD conector, SequenceManager sequenceManager) {
        this.conector = conector;
        this.sequenceManager = sequenceManager;
    }

    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoa = null;
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.id WHERE p.id = ?";

        try {
            PreparedStatement preparedStatement = conector.getPrepared(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pessoa = new PessoaJuridica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cnpj")
                );
            }

            conector.close(resultSet);
            conector.close(preparedStatement);
        } catch (SQLException e) {
        }

        return pessoa;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.id";

        try {
            ResultSet resultSet = conector.getSelect(sql);

            while (resultSet.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cnpj")
                );
                pessoas.add(pessoa);
            }

            conector.close(resultSet);
        } catch (SQLException e) {
        }

        return pessoas;
    }

    public void incluir(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";

        int novoId = sequenceManager.getValue("nome_da_sua_sequencia"); // Substitua pelo nome correto da sequência

        try {
            conector.getConnection().setAutoCommit(false);

            PreparedStatement preparedStatementPessoa = conector.getPrepared(sqlPessoa);
            preparedStatementPessoa.setString(1, pessoaJuridica.getNome());
            preparedStatementPessoa.setString(2, pessoaJuridica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaJuridica.getCidade());
            preparedStatementPessoa.setString(4, pessoaJuridica.getEstado());
            preparedStatementPessoa.setString(5, pessoaJuridica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaJuridica.getEmail());
            preparedStatementPessoa.execute();

            PreparedStatement preparedStatementPessoaJuridica = conector.getPrepared(sqlPessoaJuridica);
            preparedStatementPessoaJuridica.setInt(1, novoId);
            preparedStatementPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
            preparedStatementPessoaJuridica.execute();

            conector.getConnection().commit();
            conector.getConnection().setAutoCommit(true);

            conector.close(preparedStatementPessoa);
            conector.close(preparedStatementPessoaJuridica);
        } catch (SQLException e) {
            try {
                conector.getConnection().rollback();
                conector.getConnection().setAutoCommit(true);
            } catch (SQLException e2) {
            }
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?";

        try {
            conector.getConnection().setAutoCommit(false);

            PreparedStatement preparedStatementPessoa = conector.getPrepared(sqlPessoa);
            preparedStatementPessoa.setString(1, pessoaJuridica.getNome());
            preparedStatementPessoa.setString(2, pessoaJuridica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaJuridica.getCidade());
            preparedStatementPessoa.setString(4, pessoaJuridica.getEstado());
            preparedStatementPessoa.setString(5, pessoaJuridica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaJuridica.getEmail());
            preparedStatementPessoa.setInt(7, pessoaJuridica.getId());
            preparedStatementPessoa.execute();

            PreparedStatement preparedStatementPessoaJuridica = conector.getPrepared(sqlPessoaJuridica);
            preparedStatementPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            preparedStatementPessoaJuridica.setInt(2, pessoaJuridica.getId());
            preparedStatementPessoaJuridica.execute();

            conector.getConnection().commit();
            conector.getConnection().setAutoCommit(true);

            conector.close(preparedStatementPessoa);
            conector.close(preparedStatementPessoa);
            conector.close(preparedStatementPessoaJuridica);
        } catch (SQLException e) {
            try {
                conector.getConnection().rollback();
                conector.getConnection().setAutoCommit(true);
            } catch (SQLException e2) {
            }
        }
    }

    public void excluir(int id) {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try {
            conector.getConnection().setAutoCommit(false);

            PreparedStatement preparedStatementPessoaJuridica = conector.getPrepared(sqlPessoaJuridica);
            preparedStatementPessoaJuridica.setInt(1, id);
            preparedStatementPessoaJuridica.execute();

            PreparedStatement preparedStatementPessoa = conector.getPrepared(sqlPessoa);
            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.execute();

            conector.getConnection().commit();
            conector.getConnection().setAutoCommit(true);

            conector.close(preparedStatementPessoaJuridica);
            conector.close(preparedStatementPessoa);
        } catch (SQLException e) {
            try {
                conector.getConnection().rollback();
                conector.getConnection().setAutoCommit(true);
            } catch (SQLException e2) {
            }
        }
    }
}
