import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean sair = false;
        int opAnterior = 0; //inicia em zero por que inicialmente não possui opção anterior

        while (!sair) {
            //opções do menu principal
            System.out.println("--------------------");
            System.out.println("Menu Principal\n");
            System.out.println("1. Usuário");
            System.out.println("2. Cliente");
            System.out.println("3. Administrador");
            System.out.println("4. Livro");
            System.out.println("5. Empréstimo");
            System.out.println("6. Voltar");
            System.out.println("7. Sair");  
            
            if (opAnterior >0) {
                System.out.println("8.  Voltar a tela anterior");
                
            }
            System.out.println("--------------------");
            System.out.println("Digite uma opção: ");
            int opcao = entrada.nextInt();
            
            switch (opcao) {
                //  Usuário
                case 1:
                System.out.println("Você escolheu a opção Usuário.");
                
                opAnterior = opcao;
                    break;
                case 2:
                //  Cliente
                System.out.println("Você escolheu a opção Cliente.");

                opAnterior = opcao;
                    break;          
                case 3:
                // Adm
                System.out.println("Você escolheu a opção Administrador.");

                opAnterior = opcao;
                    break;
                case 4:
                // Livro
                System.out.println("Você escolheu a opção Livro.");

                opAnterior = opcao;
                    break;
                case 5:
                //  emprestimo
                System.out.println("Você escolheu a opção Empréstimo.");

                opAnterior = opcao;
                    break;
                case 6:
                // voltar
                System.out.println("Voltando ao menu anterior.");
                opAnterior = 0; // reseta a op anterior 
                    break;
                case 7:
                // sair
                System.out.println("Saindo do programa.");
                sair = true;
                opAnterior = opcao;
                    break;
                case 8:
                if (opAnterior == 0) {
                    System.out.println("Nenhuma opção anterior para voltar.");
                } else {
                    //volta para a tela de menu inicial
                    System.out.println("Voltando à tela anterior.");
                    int temp = opcao;
                    opcao = opAnterior;
                    opAnterior = temp;
                }
                    break;
                default:
                //mensagem de erro caso a op seja invalida
                System.out.println("Opção inválida! Tente novamente");
                    break;
            }
        }
        entrada.close();
    }
}