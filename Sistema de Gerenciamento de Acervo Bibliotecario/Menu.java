import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        boolean sair = false;
        int op;

        while (!sair) {
            // opções do Menu
            op = MenuPrincipal(sc);
            sc.nextLine();
            switch (op) {
                case 1: // Usuário
                    MenuLogin(sc, op);
                    break;
                case 2: // Cliente
                    MenuCadastro(sc, op);
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

    public static int MenuPrincipal(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Menu Principal  ===");
        System.out.println("     1 -> Login");
        System.out.println("     2 -> Registrar-se");
        System.out.println("     0 -> Sair");
        System.out.print(" >> ");
        return sc.nextInt();
    }

    public static void MenuLogin(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Menu Login  ===");
        System.out.println("     1 -> Realizar Login");
        System.out.println("     0 -> Voltar");
        System.out.print(" >> ");
        op = sc.nextInt();
        sc.nextLine();

        String email = null, senha = null;
        Usuario usuario = null;
        Adm admin = null;

        switch (op) {
            case 1:
                LimpaTela();
                System.out.println(" < Login >");
                System.out.print("Insira o email: ");
                email = sc.nextLine();
                System.out.print("Insira o a senha: ");
                senha = sc.nextLine();
                usuario = Usuario.loginUsuario(email, senha);
                if (usuario != null) {
                    admin = Adm.loginAdm(email, senha);
                    if (admin != null) {
                        menuAdmin(admin, sc);
                    } else {
                        menuUsuario(usuario, sc);
                    }
                } else {
                    System.out.println("Usuário Não Cadastrado!");
                }
                System.out.println("Aperte Enter para Continuar!");
                sc.nextLine();
                break;
            case 0:
            default:
                break;
        }

    }

    public static void MenuCadastro(Scanner sc, int op) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Tela de Cadastro  ===");
        System.out.println("     1 -> Registrar-se");
        System.out.println("     0 -> Voltar");
        System.out.print(" >> ");
        ;
        op = sc.nextInt();
        sc.nextLine();

        String nome = null, cpf = null, email = null, senha = null;
        ArrayList<String> telefone = new ArrayList<String>();

        switch (op) {
            case 1:
                LimpaTela();
                System.out.println(" < Cadastro >");
                System.out.print("Nome: ");
                nome = sc.nextLine();
                System.out.print("Cpf: ");
                cpf = sc.nextLine();
                System.out.print("Email: ");
                email = sc.nextLine();
                System.out.print("Senha: ");
                senha = sc.nextLine();
                System.out.print("Quantidade de telefones (1 ou 2): ");
                op = sc.nextInt();
                sc.nextLine();
                switch (op) {
                    case 2:
                        System.out.print("Telefone: ");
                        telefone.add(sc.nextLine());
                    case 1:
                    default:
                        System.out.print("Telefone: ");
                        telefone.add(sc.nextLine());
                        break;
                }

                System.out.println("Deseja Cadastrar sua Conta (1 -> Sim, 2 - > Não) ? ");
                op = sc.nextInt();
                sc.nextLine();
                if (op == 1) {
                    Usuario usuario = new Usuario(cpf, nome, senha, email, telefone);
                    usuario.criarConta();
                }
                System.out.println("Aperte Enter para Continuar!");
                sc.nextLine();
                break;

            case 0:
            default:
                break;
        }

    }

    public static void menuUsuario(Usuario usuario, Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        int op = 0;
        do {
            LimpaTela();
            System.out.println(" Bem Vindo " + usuario.getNome() + "!");
            System.out.println(" O que deseja fazer?");
            System.out.println(" 1 -> Exibir Perfil");
            System.out.println(" 2 -> -Buscar Livro");
            System.out.println(" 3 -> -Realizar Emprestimo");
            System.out.println(" 0 -> Sair");
            System.out.print(" > ");

            switch (sc.nextInt()) {
                case 1:
                    sc.nextLine();
                    LimpaTela();
                    System.out.println(" < Meu Perfil >");
                    System.out.println(usuario.toString());
                    System.out.println(" 1 -> Editar Perfil");
                    System.out.println(" 2 -> Excluir Conta");
                    System.out.println(" 0 -> Sair");
                    System.out.print(" > ");
                    switch (sc.nextInt()) {
                        case 1:
                            LimpaTela();
                            System.out.println("Qual dado quer editar?");
                            System.out.println(" 1 -> Nome");
                            System.out.println(" 2 -> Email");
                            System.out.println(" 3 -> Senha");
                            System.out.println(" 4 -> Telefone");
                            System.out.println(" 0 -> Voltar");
                            System.out.print(" > ");
                            switch (sc.nextInt()) {
                                case 1:
                                    sc.nextLine();
                                    System.out.print("Novo Nome: ");
                                    usuario.editarUsuario(sc.nextLine(), null, null);
                                    break;
                                case 2:
                                    sc.nextLine();
                                    System.out.print("Novo Email: ");
                                    usuario.editarUsuario(null, null, sc.nextLine());
                                    break;
                                case 3:
                                    sc.nextLine();
                                    System.out.print("Nova Senha: ");
                                    usuario.editarUsuario(null, sc.nextLine(), null);
                                    break;
                                case 4:
                                    sc.nextLine();
                                    ArrayList<String> telefone = new ArrayList<String>();
                                    System.out.print("Qual telefone deseja editar?\n");

                                    telefone = usuario.getTelefone();
                                    for (int i = 0; i < telefone.size(); i++) {
                                        if (telefone.get(i) != null)
                                            System.out.println((i + 1) + " -> " + telefone.get(i));
                                    }
                                    System.out.print(" > ");
                                    switch (sc.nextInt()) {
                                        case 2:
                                            sc.nextLine();
                                            System.out.print("Novo Telefone: ");
                                            telefone.set(1, sc.nextLine());
                                            break;
                                        case 1:
                                        default:
                                            sc.nextLine();
                                            System.out.print("Novo Telefone: ");
                                            telefone.set(0, sc.nextLine());
                                            break;
                                    }
                                    usuario.editaTelefone(telefone);

                                case 0:
                                default:
                                    System.out.println("\nAperte Enter para Continuar!");
                                    sc.nextLine();
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println(" < Excluir Conta >");
                            System.out.println(" 1 -> Excluir ");
                            System.out.println(" 0 -> Sair");
                            System.out.print(" > ");
                            switch (sc.nextInt()) {
                                case 1:
                                    sc.nextLine();
                                    System.out.print("Insira o email: ");
                                    String email = sc.nextLine();
                                    System.out.print("Insira o a senha: ");
                                    String senha = sc.nextLine();
                                    if (Usuario.loginUsuario(email, senha) != null) {
                                        Usuario.excluirConta(usuario.getCpf());
                                        System.out.println("Conta Excluida!");
                                        return;
                                    } else
                                        System.out.println("Email ou Senha Incorretos");
                                default:
                                    System.out.println("\nAperte Enter para Continuar!");
                                    sc.nextLine();
                                    break;
                            }
                            return;
                        case 0:
                        default:
                            break;
                    }
                    break;
                case 2:
                    sc.nextLine();

                    break;
                case 3:
                    sc.nextLine();

                    break;
                case 0:

                default:
                    sc.nextLine();
                    op = -1;
                    System.out.println("Saindo da Conta");
                    return;
            }
        } while (op != -1);
    }

    private static void menuAdmin(Adm admin, Scanner sc) throws InterruptedException, IOException {
        int op = 0;
        do {
            LimpaTela();
            System.out.println(" Bem Vindo Adm " + admin.getNome() + "!");
            System.out.println(" O que deseja fazer?");
            System.out.println(" 1 -> Exibir Perfil");
            System.out.println(" 2 -> Manter Usuários");
            System.out.println(" 3 -> -Manter Acervo");
            System.out.println(" 4 -> -Manter Emprestimos");
            System.out.println(" 0 -> Sair");
            System.out.print(" > ");
            switch (sc.nextInt()) {
                case 1:
                    sc.nextLine();
                    LimpaTela();
                    System.out.println(" < Meu Perfil > ");
                    System.out.println(admin.toString());
                    System.out.println(" 1 -> Editar Perfil");
                    System.out.println(" 2 -> Excluir Conta");
                    System.out.println(" 0 -> Sair");
                    System.out.print(" > ");
                    switch (sc.nextInt()) {
                        case 1:
                            LimpaTela();
                            System.out.println("Qual dado quer editar?");
                            System.out.println(" 1 -> Nome");
                            System.out.println(" 2 -> Email");
                            System.out.println(" 3 -> Senha");
                            System.out.println(" 4 -> Telefone");
                            System.out.println(" 0 -> Voltar");
                            System.out.print(" > ");
                            switch (sc.nextInt()) {
                                case 1:
                                    sc.nextLine();
                                    System.out.print("Novo Nome: ");
                                    admin.editarUsuario(sc.nextLine(), null, null);
                                    break;
                                case 2:
                                    sc.nextLine();
                                    System.out.print("Novo Email: ");
                                    admin.editarUsuario(null, null, sc.nextLine());
                                    break;
                                case 3:
                                    sc.nextLine();
                                    System.out.print("Nova Senha: ");
                                    admin.editarUsuario(null, sc.nextLine(), null);
                                    break;
                                case 4:
                                    sc.nextLine();
                                    ArrayList<String> telefone = new ArrayList<String>();
                                    System.out.print("Qual telefone deseja editar?\n");

                                    telefone = admin.getTelefone();
                                    for (int i = 0; i < telefone.size(); i++) {
                                        if (telefone.get(i) != null)
                                            System.out.println((i + 1) + " -> " + telefone.get(i));
                                    }
                                    System.out.print(" > ");
                                    switch (sc.nextInt()) {
                                        case 2:
                                            sc.nextLine();
                                            System.out.print("Novo Telefone: ");
                                            telefone.set(1, sc.nextLine());
                                            break;
                                        case 1:
                                        default:
                                            sc.nextLine();
                                            System.out.print("Novo Telefone: ");
                                            telefone.set(0, sc.nextLine());
                                            break;
                                    }
                                    admin.editaTelefone(telefone);

                                case 0:
                                default:
                                    System.out.println("\nAperte Enter para Continuar!");
                                    sc.nextLine();
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println(" < Excluir Conta >");
                            System.out.println(" 1 -> Excluir ");
                            System.out.println(" 0 -> Sair");
                            System.out.print(" > ");
                            switch (sc.nextInt()) {
                                case 1:
                                    sc.nextLine();
                                    System.out.print("Insira o email: ");
                                    String email = sc.nextLine();
                                    System.out.print("Insira o a senha: ");
                                    String senha = sc.nextLine();
                                    if (Adm.loginUsuario(email, senha) != null) {
                                        Adm.excluirAdm(admin.getCpf());
                                        System.out.println("Conta Adm Excluida!");
                                        return;
                                    } else
                                        System.out.println("Email ou Senha Incorretos");
                                default:
                                    System.out.println("\nAperte Enter para Continuar!");
                                    sc.nextLine();
                                    break;
                            }
                            return;
                        case 0:
                        default:
                            break;
                    }
                    break;
                case 2:
                    sc.nextLine();
                    LimpaTela();
                    System.out.println(" < Manter Usuários >");
                    System.out.println(" 1 -> Cadastrar ");
                    System.out.println(" 2 -> Buscar ");
                    System.out.println(" 3 -> -Listar ");
                    System.out.println(" 4 -> -Editar ");
                    System.out.println(" 5 -> Excluir ");
                    System.out.println(" 0 -> Voltar");
                    System.out.print(" > ");
                    switch (sc.nextInt()) {
                        case 1:
                            sc.nextLine();
                            LimpaTela();
                            String nome = null, cpf = null, email = null, senha = null;
                            ArrayList<String> telefone = new ArrayList<String>();

                            System.out.println(" < Cadastrar >");
                            System.out.println(" 1 -> Cadastrar Administrador");
                            System.out.println(" 2 -> Cadastrar Usuario");
                            System.out.println(" 0 -> Voltar ");
                            System.out.print(" >");

                            switch (sc.nextInt()) {
                                case 1:
                                    sc.nextLine();
                                    LimpaTela();
                                    System.out.println(" < Cadastrar Administrador >");
                                    System.out.println(" 1 -> Cadastrar Novo");
                                    System.out.println(" 2 -> -Tornar Adm");
                                    System.out.println(" 0 -> Voltar");
                                    System.out.print(" >");

                                    switch (sc.nextInt()) {
                                        case 1:
                                            sc.nextLine();
                                            LimpaTela();
                                            System.out.println(" < Cadastrar Novo Administrador >");
                                            System.out.print("Nome: ");
                                            nome = sc.nextLine();
                                            System.out.print("Cpf: ");
                                            cpf = sc.nextLine();
                                            System.out.print("Email: ");
                                            email = sc.nextLine();
                                            System.out.print("Senha: ");
                                            senha = sc.nextLine();
                                            System.out.print("Quantidade de telefones (1 ou 2): ");
                                            op = sc.nextInt();
                                            sc.nextLine();
                                            switch (op) {
                                                case 2:
                                                    System.out.print("Telefone: ");
                                                    telefone.add(sc.nextLine());
                                                case 1:
                                                default:
                                                    System.out.print("Telefone: ");
                                                    telefone.add(sc.nextLine());
                                                    break;
                                            }

                                            System.out.println("Deseja Cadastrar Adm (1 -> Sim, 2 - > Não) ? ");
                                            op = sc.nextInt();
                                            sc.nextLine();

                                            if (op == 1) {
                                                Adm admaux = new Adm(cpf, nome, senha, email, telefone);
                                                admaux.criarAdm();
                                            }
                                            System.out.println("Aperte Enter para Continuar!");
                                            sc.nextLine();
                                            break;
                                        case 2:
                                            sc.nextLine();
                                            break;
                                        case 0:
                                        default:
                                            sc.nextLine();
                                            break;
                                    }
                                    break;
                                case 2:
                                    sc.nextLine();
                                    LimpaTela();
                                    System.out.println(" < Cadastro >");
                                    System.out.print("Nome: ");
                                    nome = sc.nextLine();
                                    System.out.print("Cpf: ");
                                    cpf = sc.nextLine();
                                    System.out.print("Email: ");
                                    email = sc.nextLine();
                                    System.out.print("Senha: ");
                                    senha = sc.nextLine();
                                    System.out.print("Quantidade de telefones (1 ou 2): ");
                                    op = sc.nextInt();
                                    sc.nextLine();
                                    switch (op) {
                                        case 2:
                                            System.out.print("Telefone: ");
                                            telefone.add(sc.nextLine());
                                        case 1:
                                        default:
                                            System.out.print("Telefone: ");
                                            telefone.add(sc.nextLine());
                                            break;
                                    }

                                    System.out.println("Deseja Cadastrar Usuário (1 -> Sim, 2 - > Não) ? ");
                                    op = sc.nextInt();
                                    sc.nextLine();
                                    if (op == 1) {
                                        Usuario usuario = new Usuario(cpf, nome, senha, email, telefone);
                                        usuario.criarConta();
                                    }
                                    System.out.println("Aperte Enter para Continuar!");
                                    sc.nextLine();
                                    break;

                                default:
                                    break;
                            }

                            break;
                        case 2:
                            sc.nextLine();
                            LimpaTela();
                            System.out.println(" < Buscar Usuário >");
                            System.out.print(" Cpf: ");
                            cpf = sc.nextLine();
                            if (Adm.buscaAdm(cpf) != null) {
                                System.out.println(Adm.buscaAdm(cpf).toString());
                            } else if (Usuario.buscaUsuario(cpf) != null) {
                                System.out.println(Usuario.buscaUsuario(cpf));
                            } else {
                                System.out.println("Usuário não encontrado!");
                            }
                            System.out.println("\nAperte Enter para Continuar!");
                            sc.nextLine();
                            break;
                        case 3:
                            sc.nextLine();
                            break;
                        case 4:
                            sc.nextLine();
                            break;

                        case 5:
                            sc.nextLine();
                            LimpaTela();
                            System.out.println("< Excluir Usuário >");
                            System.out.println(" 1 -> Excluir");
                            System.out.println(" 0 -> Voltar");
                            System.out.print(" > ");
                            switch (sc.nextInt()) {
                                case 1:
                                    sc.nextLine();
                                    LimpaTela();
                                    System.out.println(" < Excluir Usuário >");
                                    System.out.print(" Cpf: ");
                                    cpf = sc.nextLine();
                                    if (Adm.buscaAdm(cpf) != null) {
                                        System.out.println(Adm.buscaAdm(cpf).toString());
                                    } else if (Usuario.buscaUsuario(cpf) != null) {
                                        System.out.println(Usuario.buscaUsuario(cpf));
                                    } else {
                                        System.out.println("Usuário não encontrado!");
                                        break;
                                    }
                                    System.out.println("Deseja Excluir o Usuário (1 -> Sim, 2 - > Não)? ");
                                    switch (sc.nextInt()) {
                                        case 1:
                                            sc.nextLine();
                                            Adm.excluirAdm(cpf);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                    
                                    case 0:
                                    default:
                                    sc.nextLine();
                                    break;
                                }
                                System.out.println("\nAperte Enter para Continuar!");
                                sc.nextLine();
                            break;
                        case 0:
                        default:
                            sc.nextLine();
                            System.out.println("\nAperte Enter para Continuar!");
                            sc.nextLine();
                            break;
                    }
                    break;
                case 3:
                    LimpaTela();
                    System.out.println(" < Manter Administradores >");
                    System.out.println(" 1 -> Cadastrar ");
                    System.out.println(" 2 -> Buscar ");
                    System.out.println(" 3 -> Listar ");
                    System.out.println(" 4 -> Editar ");
                    System.out.println(" 5 -> Excluir ");
                    System.out.println(" 0 -> Voltar");
                    sc.nextLine();
                    break;
                case 4:
                    sc.nextLine();
                    break;
                case 5:

                default:
                    sc.nextLine();
                    System.out.println("Saindo da Conta Adm");
                    op = -1;
                    return;
            }
        } while (op != -1);
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

}
