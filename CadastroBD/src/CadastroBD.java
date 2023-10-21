import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.Scanner;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

public class CadastroBD {
    public static void main(String[] args) {
        ConectorBD conector = new ConectorBD("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;", "loja", "loja");
        SequenceManager sequenceManager = new SequenceManager(conector);

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector, sequenceManager);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conector, sequenceManager);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                    int tipoCadastro = scanner.nextInt();
                    switch (tipoCadastro) {
                        case 1 -> {
                            // Cadastro de Pessoa Física
                            PessoaFisica pessoaFisica = new PessoaFisica();
                            // Solicitar os dados do usuário via teclado
                            System.out.println("Nome: ");
                            pessoaFisica.setNome(scanner.next());
                            // Preencher os demais atributos da pessoa física
                            // Chamar o método pessoaFisicaDAO.incluir(objetoPessoaFisica)
                            pessoaFisicaDAO.incluir(pessoaFisica);
                    }
                        case 2 -> {
                            // Cadastro de Pessoa Jurídica
                            PessoaJuridica pessoaJuridica = new PessoaJuridica();
                            // Solicitar os dados do usuário via teclado
                            System.out.println("Nome: ");
                            pessoaJuridica.setNome(scanner.next());
                            // Preencher os demais atributos da pessoa jurídica
                            // Chamar o método pessoaJuridicaDAO.incluir(objetoPessoaJuridica)
                            pessoaJuridicaDAO.incluir(pessoaJuridica);
                    }
                        default -> System.out.println("Opção inválida.");
                    }
                }
                case 2 -> {
                    System.out.println("Selecione o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                    int tipoAlteracao = scanner.nextInt();
                    switch (tipoAlteracao) {
                        case 1 -> {
                            // Implementar opção para alterar Pessoa Física
                            System.out.println("ID da Pessoa Física a ser alterada: ");
                            int id = scanner.nextInt();
                            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);
                            if (pessoaFisica != null) {
                                // Apresentar os dados atuais
                                System.out.println("Dados atuais:");
                                System.out.println(pessoaFisica);
                                // Solicitar os novos dados
                                System.out.println("Novo nome: ");
                                pessoaFisica.setNome(scanner.next());
                                // Preencher os demais atributos que deseja alterar
                                // Chamar o método pessoaFisicaDAO.alterar(pessoaFisica)
                                pessoaFisicaDAO.alterar(pessoaFisica);
                            } else {
                                System.out.println("Pessoa Física não encontrada.");
                            }
                    }
                        case 2 -> {
                            // Implementar opção para alterar Pessoa Jurídica
                            System.out.println("ID da Pessoa Jurídica a ser alterada: ");
                            int id = scanner.nextInt();
                            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);
                            if (pessoaJuridica != null) {
                                // Apresentar os dados atuais
                                System.out.println("Dados atuais:");
                                System.out.println(pessoaJuridica);
                                // Solicitar os novos dados
                                System.out.println("Novo nome: ");
                                pessoaJuridica.setNome(scanner.next());
                                // Preencher os demais atributos que deseja alterar
                                // Chamar o método pessoaJuridicaDAO.alterar(pessoaJuridica)
                                pessoaJuridicaDAO.alterar(pessoaJuridica);
                            } else {
                                System.out.println("Pessoa Jurídica não encontrada.");
                            }
                    }
                        default -> System.out.println("Opção inválida.");
                    }
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
            // Implementar opção para excluir
            // Implementar opção para exibir pelo ID
            // Implementar opção para exibir todos
                    } while (opcao != 0);
    }
}
