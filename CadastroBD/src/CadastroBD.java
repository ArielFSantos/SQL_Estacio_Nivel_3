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
            scanner.nextLine(); // Consume the newline character

            switch (opcao) {
                case 1 -> {
                    System.out.println("Selecione o tipo (F - Pessoa Física, J - Pessoa Jurídica):");
                    String tipoCadastro = scanner.nextLine();
                    switch (tipoCadastro) {
                        case "F"  -> {
                                // Cadastro de Pessoa Física
                                PessoaFisica pessoaFisica = new PessoaFisica();

                                // Solicitar os dados do usuário via teclado
                                System.out.println("Nome: ");
                                pessoaFisica.setNome(scanner.next());

                                // Solicitar outros atributos da Pessoa Física (como CPF, logradouro, cidade, etc.)
                                System.out.println("CPF: ");
                                pessoaFisica.setCpf(scanner.next());
                                System.out.println("Logradouro: ");
                                pessoaFisica.setLogradouro(scanner.next());
                                System.out.println("Cidade: ");
                                pessoaFisica.setCidade(scanner.next());
                                System.out.println("Estado: ");
                                pessoaFisica.setEstado(scanner.next());
                                System.out.println("Telefone: ");
                                pessoaFisica.setTelefone(scanner.next());
                                System.out.println("Email: ");
                                pessoaFisica.setEmail(scanner.next());

                                // Chamar o método pessoaFisicaDAO.incluir(objetoPessoaFisica)
                                pessoaFisicaDAO.incluir(pessoaFisica);
                        }
                        case "J" -> {
                            // Cadastro de Pessoa Jurídica
                            PessoaJuridica pessoaJuridica = new PessoaJuridica();

                            // Solicitar os dados do usuário via teclado
                            System.out.println("Nome: ");
                            pessoaJuridica.setNome(scanner.next());

                            // Solicitar os demais dados via teclado (por exemplo, CNPJ, telefone, email, estado, cidade, logradouro)
                            System.out.println("CNPJ: ");
                            pessoaJuridica.setCnpj(scanner.next());

                            System.out.println("Telefone: ");
                            pessoaJuridica.setTelefone(scanner.next());

                            System.out.println("Email: ");
                            pessoaJuridica.setEmail(scanner.next());

                            System.out.println("Estado: ");
                            pessoaJuridica.setEstado(scanner.next());

                            System.out.println("Cidade: ");
                            pessoaJuridica.setCidade(scanner.next());

                            System.out.println("Logradouro: ");
                            pessoaJuridica.setLogradouro(scanner.next());

                            // Chamar o método pessoaJuridicaDAO.incluir(objetoPessoaJuridica)
                            pessoaJuridicaDAO.incluir(pessoaJuridica);
                        }
                        default -> System.out.println("Opção inválida.");
                    }
                }
                case 2 -> {
                    System.out.println("Selecione o tipo (F - Pessoa Física, J - Pessoa Jurídica):");
                    String tipoAlteracao = scanner.nextLine();
                    switch (tipoAlteracao) {
                        case "F" -> {
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

                                System.out.println("Novo CPF: ");
                                pessoaFisica.setCpf(scanner.next());

                                System.out.println("Novo Logradouro: ");
                                pessoaFisica.setLogradouro(scanner.next());

                                System.out.println("Nova Cidade: ");
                                pessoaFisica.setCidade(scanner.next());

                                System.out.println("Novo Estado: ");
                                pessoaFisica.setEstado(scanner.next());

                                System.out.println("Novo Telefone: ");
                                pessoaFisica.setTelefone(scanner.next());

                                System.out.println("Novo Email: ");
                                pessoaFisica.setEmail(scanner.next());

                                // Chamar o método pessoaFisicaDAO.alterar(pessoaFisica)
                                pessoaFisicaDAO.alterar(pessoaFisica);
                                System.out.println("Pessoa Física alterada com sucesso.");
                            } else {
                                System.out.println("Pessoa Física não encontrada.");
                            }
                        }

                    
                        case "J" -> {
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

                                System.out.println("Novo CNPJ: ");
                                pessoaJuridica.setCnpj(scanner.next());

                                System.out.println("Novo Logradouro: ");
                                pessoaJuridica.setLogradouro(scanner.next());

                                System.out.println("Nova Cidade: ");
                                pessoaJuridica.setCidade(scanner.next());

                                System.out.println("Novo Estado: ");
                                pessoaJuridica.setEstado(scanner.next());

                                System.out.println("Novo Telefone: ");
                                pessoaJuridica.setTelefone(scanner.next());

                                System.out.println("Novo Email: ");
                                pessoaJuridica.setEmail(scanner.next());

                                // Chamar o método pessoaJuridicaDAO.alterar(pessoaJuridica)
                                pessoaJuridicaDAO.alterar(pessoaJuridica);
                                System.out.println("Pessoa Jurídica alterada com sucesso.");
                            } else {
                                System.out.println("Pessoa Jurídica não encontrada.");
                            }
                        }

                        default -> System.out.println("Opção inválida.");
                    }
                }
                case 3 -> {
    System.out.println("Selecione o tipo (F - Pessoa Física, J - Pessoa Jurídica):");
    String tipoExclusao = scanner.nextLine();

    switch (tipoExclusao) {
        case "F" -> {
            System.out.println("ID da Pessoa Física a ser excluída: ");
            int id = scanner.nextInt();
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

            if (pessoaFisica != null) {
                System.out.println("Dados da Pessoa Física a ser excluída:");
                System.out.println(pessoaFisica);

                System.out.println("Tem certeza de que deseja excluir esta Pessoa Física? (S/N)");
                String confirmacao = scanner.next();

                if (confirmacao.equalsIgnoreCase("S")) {
                    pessoaFisicaDAO.excluir(id);
                    System.out.println("Pessoa Física excluída com sucesso.");
                } else {
                    System.out.println("Operação de exclusão cancelada.");
                }
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        }

        case "J" -> {
            System.out.println("ID da Pessoa Jurídica a ser excluída: ");
            int id = scanner.nextInt();
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

            if (pessoaJuridica != null) {
                System.out.println("Dados da Pessoa Jurídica a ser excluída:");
                System.out.println(pessoaJuridica);

                System.out.println("Tem certeza de que deseja excluir esta Pessoa Jurídica? (S/N)");
                String confirmacao = scanner.next();

                if (confirmacao.equalsIgnoreCase("S")) {
                    pessoaJuridicaDAO.excluir(id);
                    System.out.println("Pessoa Jurídica excluída com sucesso.");
                } else {
                    System.out.println("Operação de exclusão cancelada.");
                }
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        }

        default -> System.out.println("Opção inválida.");
    }
}
                case 4 -> {
    System.out.println("Selecione o tipo (F - Pessoa Física, J - Pessoa Jurídica):");
    String tipoExibirPorID = scanner.nextLine();

    switch (tipoExibirPorID) {
        case "F" -> {
            System.out.println("ID da Pessoa Física a ser exibida: ");
            int id = scanner.nextInt();
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

            if (pessoaFisica != null) {
                System.out.println("Dados da Pessoa Física:");
                System.out.println(pessoaFisica);
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        }

        case "J" -> {
            System.out.println("ID da Pessoa Jurídica a ser exibida: ");
            int id = scanner.nextInt();
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

            if (pessoaJuridica != null) {
                System.out.println("Dados da Pessoa Jurídica:");
                System.out.println(pessoaJuridica);
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        }

        default -> System.out.println("Opção inválida.");
    }
}
                case 5 -> {
                    System.out.println("Selecione o tipo (F - Pessoa Física, J - Pessoa Jurídica):");
                    String tipoExibirTodos = scanner.nextLine();

                    switch (tipoExibirTodos) {
                        case "F" -> {
                            System.out.println("Pessoas Físicas no Banco:");
                            pessoaFisicaDAO.getPessoas().forEach(pf -> {
                                System.out.println(pf);
                            });
                        }

                        case "J" -> {
                            System.out.println("Pessoas Jurídicas no Banco:");
                            pessoaJuridicaDAO.getPessoas().forEach(pj -> {
                                System.out.println(pj);
                            });
                        }

                        default -> System.out.println("Opção inválida.");
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
      
                    } while (opcao != 0);
    }
}
