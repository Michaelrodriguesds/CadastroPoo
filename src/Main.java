import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente

            switch (opcao) {
                case 1:
                    incluir(scanner, repoFisica, repoJuridica);
                    break;
                case 2:
                    alterar(scanner, repoFisica, repoJuridica);
                    break;
                case 3:
                    excluir(scanner, repoFisica, repoJuridica);
                    break;
                case 4:
                    exibirPorId(scanner, repoFisica, repoJuridica);
                    break;
                case 5:
                    exibirTodos(scanner, repoFisica, repoJuridica);
                    break;
                case 6:
                    salvar(scanner, repoFisica, repoJuridica);
                    break;
                case 7:
                    recuperar(scanner, repoFisica, repoJuridica);
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Incluir");
        System.out.println("2. Alterar");
        System.out.println("3. Excluir");
        System.out.println("4. Exibir pelo ID");
        System.out.println("5. Exibir todos");
        System.out.println("6. Salvar dados");
        System.out.println("7. Recuperar dados");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void incluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Escolha o tipo (1. Física, 2. Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (tipo == 1) {
            PessoaFisica pessoa = lerDadosPessoaFisica(scanner);
            repoFisica.inserir(pessoa);
            System.out.println("Pessoa Física incluída com sucesso!");
        } else if (tipo == 2) {
            PessoaJuridica empresa = lerDadosPessoaJuridica(scanner);
            repoJuridica.inserir(empresa);
            System.out.println("Pessoa Jurídica incluída com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static PessoaFisica lerDadosPessoaFisica(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        return new PessoaFisica(id, nome, cpf, idade);
    }

    private static PessoaJuridica lerDadosPessoaJuridica(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente
        System.out.print("Nome da Empresa: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(id, nome, cnpj);
    }

    private static void alterar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Escolha o tipo (1. Física, 2. Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (tipo == 1) {
            System.out.print("ID da Pessoa Física: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente
            PessoaFisica pessoaAtual = repoFisica.obter(id);

            if (pessoaAtual != null) {
                System.out.println("Dados atuais da Pessoa Física:");
                pessoaAtual.exibir();

                PessoaFisica novosDados = lerDadosPessoaFisica(scanner);
                repoFisica.alterar(novosDados);
                System.out.println("Pessoa Física alterada com sucesso!");
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("ID da Pessoa Jurídica: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente
            PessoaJuridica empresaAtual = repoJuridica.obter(id);

            if (empresaAtual != null) {
                System.out.println("Dados atuais da Pessoa Jurídica:");
                empresaAtual.exibir();

                PessoaJuridica novosDados = lerDadosPessoaJuridica(scanner);
                repoJuridica.alterar(novosDados);
                System.out.println("Pessoa Jurídica alterada com sucesso!");
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Escolha o tipo (1. Física, 2. Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (tipo == 1) {
            System.out.print("ID da Pessoa Física: ");
            int id = scanner.nextInt();
            repoFisica.excluir(id);
            System.out.println("Pessoa Física excluída com sucesso!");
        } else if (tipo == 2) {
            System.out.print("ID da Pessoa Jurídica: ");
            int id = scanner.nextInt();
            repoJuridica.excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void exibirPorId(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Escolha o tipo (1. Física, 2. Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (tipo == 1) {
            System.out.print("ID da Pessoa Física: ");
            int id = scanner.nextInt();
            PessoaFisica pessoa = repoFisica.obter(id);

            if (pessoa != null) {
                System.out.println("Dados da Pessoa Física:");
                pessoa.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("ID da Pessoa Jurídica: ");
            int id = scanner.nextInt();
            PessoaJuridica empresa = repoJuridica.obter(id);

            if (empresa != null) {
                System.out.println("Dados da Pessoa Jurídica:");
                empresa.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Escolha o tipo (1. Física, 2. Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (tipo == 1) {
            List<PessoaFisica> pessoasFisicas = repoFisica.obterTodos();
            System.out.println("Dados de Pessoas Físicas:");
            for (PessoaFisica pessoa : pessoasFisicas) {
                pessoa.exibir();
            }
        } else if (tipo == 2) {
            List<PessoaJuridica> empresas = repoJuridica.obterTodos();
            System.out.println("Dados de Pessoas Jurídicas:");
            for (PessoaJuridica empresa : empresas) {
                empresa.exibir();
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void salvar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("Digite o prefixo dos arquivos: ");
            String prefixo = scanner.next();
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private static void recuperar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("Digite o prefixo dos arquivos: ");
            String prefixo = scanner.next();
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}
