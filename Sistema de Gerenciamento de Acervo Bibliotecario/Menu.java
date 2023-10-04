import java.io.IOException;
import java.util.Scanner;

public class MainProjeto {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        boolean sair = false;
        int op;

        while (!sair) {
            // opções do MainProjeto Testes
            op = MenuTestes(sc);
            sc.nextLine();
            switch (op) {
                case 1: // Usuário
                    MenuUsuario(sc, op);
                    break;
                case 2: // Cliente
                    MenuCliente(sc, op);
                    break;
                case 3: // Adm
                    MenuAdm(sc, op);
                    break;
                case 4: // Livro
                    MenuLivro(sc, op);
                    break;
                case 5: // Emprestimo
                    MenuEmprestimo(sc, op);
                    break;
                case 0: // Sair
                    sair = true;
                    return;
                default: // Opção inválida
                    sair = true;
                    return;
            }
        }
        sc.close();
    }

    public static int MenuTestes(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("-------------------------");
        System.out.println("   Menu Testes\n");
        System.out.println("    1 -> Usuário");
        System.out.println("    2 -> Cliente");
        System.out.println("    3 -> Administrador");
        System.out.println("    4 -> Livro");
        System.out.println("    5 -> Empréstimo");
        System.out.println("    0 -> Sair");
        System.out.println("-------------------------");
        System.out.println(" Digite uma opção: ");
        return sc.nextInt();
    }

    public static void MenuUsuario(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("-------------------------");
        System.out.println("   Menu Usuário\n");
        System.out.println("    1 -> Inserir");
        System.out.println("    2 -> Buscar");
        System.out.println("    3 -> Editar");
        System.out.println("    4 -> Remover");
        System.out.println("    0 -> Voltar");
        System.out.println("-------------------------");
        System.out.println(" Digite uma opção: ");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                LimpaTela();
                System.out.println("Insira os dados do Usuário: ");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Cpf: ");
                String cpf = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                System.out.println("Telefone");
                System.out.println(" 1 -> Inserir 1");
                System.out.println(" 2 -> Inserir 2");
                System.out.print(" >> ");
                String[] telefone = new String[2];
                op = sc.nextInt();
                sc.nextLine();
                switch (op) {
                    case 2:
                        System.out.print("Telefone 1:");
                        telefone[0] = sc.nextLine();
                        sc.nextLine();
                        System.out.print("Telefone 2:");
                        telefone[1] = sc.nextLine();
                        sc.nextLine();
                        break;
                    case 1:
                    default:
                        System.out.print("Telefone:");
                        telefone[0] = sc.nextLine();
                        sc.nextLine();
                        break;
                }
                Usuario usuario = new Usuario(cpf, nome, senha, email, telefone);
                usuario.insereUsuario();
                sc.nextLine();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:
            default:
                break;
        }
    }

    public static void MenuCliente(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("-------------------------");
        System.out.println("   Menu Cliente\n");
        System.out.println("    1 -> Inserir");
        System.out.println("    2 -> Buscar");
        System.out.println("    3 -> Editar");
        System.out.println("    4 -> Remover");
        System.out.println("    0 -> Voltar");
        System.out.println("-------------------------");
        System.out.println(" Digite uma opção: ");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:
            default:
                break;
        }
    }

    public static void MenuAdm(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("-------------------------");
        System.out.println("   Menu Adm\n");
        System.out.println("    1 -> Inserir");
        System.out.println("    2 -> Buscar");
        System.out.println("    3 -> Editar");
        System.out.println("    4 -> Remover");
        System.out.println("    0 -> Voltar");
        System.out.println("-------------------------");
        System.out.println(" Digite uma opção: ");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:
            default:
                break;
        }
    }

    // idLivro Serial PRIMARY KEY,
    // Titulo VARCHAR(50),
    // Genero VARCHAR(20),
    // Autor VARCHAR(255),
    // DataPublicacao Date,
    // Edicao VARCHAR(50),
    // Editora VARCHAR(50),
    // ISBN VARCHAR(13),
    // quantLivros int,
    // quantDisponivel int

    public static void MenuLivro(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("-------------------------");
        System.out.println("   Menu Livro\n");
        System.out.println("    1 -> Inserir");
        System.out.println("    2 -> Buscar");
        System.out.println("    3 -> Editar");
        System.out.println("    4 -> Remover");
        System.out.println("    0 -> Voltar");
        System.out.println("-------------------------");
        System.out.println(" Digite uma opção: ");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                LimpaTela();
                System.out.println("Insira os dados do livro");
                System.out.print("Titulo: ");
                String titulo = sc.nextLine();
                System.out.print("Genero: ");
                String genero = sc.nextLine();
                System.out.print("Autor: ");
                String autor = sc.nextLine();
                System.out.print("Data da Publicacao (dd/MM/yyyy): ");
                String dataPublicacao = sc.nextLine();
                System.out.println("Edicao: ");
                String edicao = sc.nextLine();
                System.out.println("Editora: ");
                String editora = sc.nextLine();
                System.out.println("ISBN: ");
                String isbn = sc.nextLine();
                System.out.println("quantLivros: ");
                int quantLivros = sc.nextInt();
                System.out.println("quantDisponivel: ");
                int quantDisponivel = sc.nextInt();
                Livro livroAux = new Livro(titulo, genero, autor, dataPublicacao, edicao, editora, isbn, quantLivros,
                        quantDisponivel);
                livroAux.inserirLivro();
                System.out.println(livroAux);
                sc.nextLine();
                sc.nextLine();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:
            default:
                break;
        }
    }

    public static void MenuEmprestimo(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("-------------------------");
        System.out.println("   Menu Emprestimo\n");
        System.out.println("    1 -> Inserir");
        System.out.println("    2 -> Buscar");
        System.out.println("    3 -> Editar");
        System.out.println("    4 -> Remover");
        System.out.println("    0 -> Voltar");
        System.out.println("-------------------------");
        System.out.println(" Digite uma opção: ");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:
            default:
                break;
        }
    }

    public static void LimpaTela() throws InterruptedException, IOException {
        // Isso aqui funciona pra identificar qual SO está sendo usado
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
        }
    }

    public static void MenuLogin(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("Menu Login\n");
        System.out.println("1. Login");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");

        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                LimpaTela();
                System.out.println("Insira o e-mail: ");
                String email = sc.nextLine();
                System.out.println("Insira o login: ");
                String login = sc.nextLine();
                break;
            case 0:

            default:
                break;
        }

    }

    public static void MenuCadastro(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println("Tela de Cadastro\n");
        System.out.println("1. Registrar-se");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");

        op = sc.nextInt();sc.nextLine();
        switch (op) {
            case 1:
                LimpaTela();
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Cpf: ");
                String cpf = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                System.out.println("Telefone");
                System.out.println(" 1 -> Inserir 1");
                System.out.println(" 2 -> Inserir 2");
                System.out.print(" >> ");
                String[] telefone = new String[2];
                op = sc.nextInt();
                sc.nextLine();
                break;
            case 0:

            default:
                break;
        }

    }
}
