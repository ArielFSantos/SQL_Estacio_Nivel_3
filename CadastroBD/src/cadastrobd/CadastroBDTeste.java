package cadastrobd;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import cadastrobd.model.util.SequenceManager;

public class CadastroBDTeste {
    public static void main(String[] args) {
        try {
            // Configurar o ConectorBD e o SequenceManager de acordo com o seu ambiente
            ConectorBD conector = new ConectorBD("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;", "loja", "loja");
            SequenceManager sequenceManager = new SequenceManager(conector);

            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector, sequenceManager);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conector, sequenceManager);

            // Operações com Pessoa Física
            PessoaFisica pessoaFisica = new PessoaFisica(0, "João", "Rua A", "Cidade A", "Estado A", "123456789", "joao@example.com", "12345678900");

            // Incluir pessoa física
            pessoaFisicaDAO.incluir(pessoaFisica);

            // Alterar pessoa física
            pessoaFisica.setNome("João da Silva");
            pessoaFisicaDAO.alterar(pessoaFisica);

            // Consultar todas as pessoas físicas e listar no console
            System.out.println("Pessoas Físicas no Banco:");
            for (PessoaFisica pf : pessoaFisicaDAO.getPessoas()) {
                System.out.println(pf);
            }

            // Excluir pessoa física
            pessoaFisicaDAO.excluir(pessoaFisica.getId());

            // Operações com Pessoa Jurídica
            PessoaJuridica pessoaJuridica = new PessoaJuridica(0, "Empresa XYZ", "Av. B", "Cidade B", "Estado B", "987654321", "empresa@example.com", "12345678901234");

            // Incluir pessoa jurídica
            pessoaJuridicaDAO.incluir(pessoaJuridica);

            // Alterar pessoa jurídica
            pessoaJuridica.setNome("Nova Empresa XYZ");
            pessoaJuridicaDAO.alterar(pessoaJuridica);

            // Consultar todas as pessoas jurídicas e listar no console
            System.out.println("Pessoas Jurídicas no Banco:");
            pessoaJuridicaDAO.getPessoas().forEach(pj -> {
                System.out.println(pj.getNome()); // Supondo que você queira exibir o nome da pessoa jurídica
            });


            // Excluir pessoa jurídica
            pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
        } catch (Exception e) {
            // Lide com exceções apropriadamente.
            
        }
    }
}
