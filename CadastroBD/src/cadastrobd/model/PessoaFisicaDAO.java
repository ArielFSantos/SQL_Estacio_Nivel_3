
package cadastrobd.model;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PessoaFisicaDAO {
    private final ConectorBD conector;
    private final SequenceManager sequenceManager;

    public PessoaFisicaDAO(ConectorBD conector, SequenceManager sequenceManager) {
        this.conector = conector;
        this.sequenceManager = sequenceManager;
    }

    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoa = null;
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.id WHERE p.id = ?";

        try {
            PreparedStatement preparedStatement = conector.getPrepared(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pessoa = new PessoaFisica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cpf")
                );
            }

            conector.close(resultSet);
            conector.close(preparedStatement);
        } catch (SQLException e) {
        }

        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.id";

        try {
            ResultSet resultSet = conector.getSelect(sql);

            while (resultSet.next()) {
                PessoaFisica pessoa = new PessoaFisica(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("logradouro"),
                    resultSet.getString("cidade"),
                    resultSet.getString("estado"),
                    resultSet.getString("telefone"),
                    resultSet.getString("email"),
                    resultSet.getString("cpf")
                );
                pessoas.add(pessoa);
            }

            conector.close(resultSet);
        } catch (SQLException e) {
        }

        return pessoas;
    }

    public void incluir(PessoaFisica pessoaFisica) {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";

        int novoId = sequenceManager.getValue("nome_da_sua_sequencia"); // Substitua pelo nome correto da sequência

        try {
            conector.getConnection().setAutoCommit(false);

            PreparedStatement preparedStatementPessoa = conector.getPrepared(sqlPessoa);
            preparedStatementPessoa.setString(1, pessoaFisica.getNome());
            preparedStatementPessoa.setString(2, pessoaFisica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaFisica.getCidade());
            preparedStatementPessoa.setString(4, pessoaFisica.getEstado());
            preparedStatementPessoa.setString(5, pessoaFisica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaFisica.getEmail());
            preparedStatementPessoa.execute();

            PreparedStatement preparedStatementPessoaFisica = conector.getPrepared(sqlPessoaFisica);
            preparedStatementPessoaFisica.setInt(1, novoId);
            preparedStatementPessoaFisica.setString(2, pessoaFisica.getCpf());
            preparedStatementPessoaFisica.execute();

            conector.getConnection().commit();
            conector.getConnection().setAutoCommit(true);

            conector.close(preparedStatementPessoa);
            conector.close(preparedStatementPessoaFisica);
        } catch (SQLException e) {
            try {
                conector.getConnection().rollback();
                conector.getConnection().setAutoCommit(true);
            } catch (SQLException e2) {
            }
        }
    }

    public void alterar(PessoaFisica pessoaFisica) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";

        try {
            conector.getConnection().setAutoCommit(false);

            PreparedStatement preparedStatementPessoa = conector.getPrepared(sqlPessoa);
            preparedStatementPessoa.setString(1, pessoaFisica.getNome());
            preparedStatementPessoa.setString(2, pessoaFisica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaFisica.getCidade());
            preparedStatementPessoa.setString(4, pessoaFisica.getEstado());
            preparedStatementPessoa.setString(5, pessoaFisica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaFisica.getEmail());
            preparedStatementPessoa.setInt(7, pessoaFisica.getId());
            preparedStatementPessoa.execute();

            PreparedStatement preparedStatementPessoaFisica = conector.getPrepared(sqlPessoaFisica);
            preparedStatementPessoaFisica.setString(1, pessoaFisica.getCpf());
            preparedStatementPessoaFisica.setInt(2, pessoaFisica.getId());
            preparedStatementPessoaFisica.execute();

            conector.getConnection().commit();
            conector.getConnection().setAutoCommit(true);

            conector.close(preparedStatementPessoa);
            conector.close(preparedStatementPessoaFisica);
        } catch (SQLException e) {
            try {
                conector.getConnection().rollback();
                conector.getConnection().setAutoCommit(true);
            } catch (SQLException e2) {
            }
        }
    }

    public void excluir(int id) {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try {
            conector.getConnection().setAutoCommit(false);

            PreparedStatement preparedStatementPessoaFisica = conector.getPrepared(sqlPessoaFisica);
            preparedStatementPessoaFisica.setInt(1, id);
            preparedStatementPessoaFisica.execute();

            PreparedStatement preparedStatementPessoa = conector.getPrepared(sqlPessoa);
            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.execute();

            conector.getConnection().commit();
            conector.getConnection().setAutoCommit(true);

            conector.close(preparedStatementPessoaFisica);
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

