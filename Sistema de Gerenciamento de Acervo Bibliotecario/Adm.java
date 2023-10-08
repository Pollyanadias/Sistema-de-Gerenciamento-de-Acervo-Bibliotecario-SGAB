import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

    public class Adm extends Usuario {
    private int idAdm;

    public Adm(int idAdm, Usuario usuario) {
        super(usuario.getCpf(),usuario.getNome(), usuario.getSenha(), usuario.getEmail());
        this.idAdm = idAdm;
    }

    public Adm(String cpf, String nome, String senha, String email, ArrayList<String> telefone, int idAdm) {
        super(cpf, nome, senha, email);
        this.idAdm = idAdm;
    }

    public int getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(int idAdm) {
        this.idAdm = idAdm;
    }

    public void criarAdm() {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;

        if (buscaUsuario(getCpf()) == null && buscaAdm(getCpf()) == null) {
            try {
                super.criarConta();
                String query = "INSERT INTO Adm (cpf) VALUES (?)";
                state = connection.prepareStatement(query);
                state.setString(1, super.getCpf());
                state.executeUpdate();
                state.close();
                System.out.println("Adm Criado!");
            } catch (Exception e) {
                System.out.println(e);

            }
        } else if (buscaUsuario(getCpf()) == null) {
            try {
                String query = "INSERT INTO Adm (cpf) VALUES (?)";
                state = connection.prepareStatement(query);
                state.setString(1, super.getCpf());
                state.executeUpdate();
                state.close();
                System.out.println("Adm Criado!");
            } catch (Exception e) {
                System.out.println(e);

            }
        } else {
            System.out.println("Adm já cadastrado!");
        }

    }

public static Adm buscaAdm(String cpf) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            // Busca por pelo cpf
            String query = "SELECT idAdm from Adm where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            result = state.executeQuery();

            if (result.next()) {
                return new Adm(result.getInt(1), Usuario.buscaUsuario(cpf));
            }
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
   
    public static void ExcluirAdm(String cpf){
        try(Connection connection = PostgreSQLConnection.getInstance().getConnection() ) {
        String query = "DELETE * FROM Adm WHERE cpf = ?"; //Vai deletar todos os dados do ADM que possue o CPF correpondente à o requisitado
        PreparedStatement state = connection.prepareStatement(query);
        state.setString(1, cpf); //substituindo o ? da String query, o 1 é a posição e o valor cpf é ?
        state.executeUpdate();
        Usuario.excluirConta(cpf); 
        } catch (Exception e) {
            e.printStackTrace(); //mostrar o erro onde ele está
        }
     }

    public void editarAdm(String cpf, String nome, String senha, String email, String telefone1, String telefone2, String telefone3 ){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "UPDATE Adm SET nome = ?, senha = ?, email = ? WHERE cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, nome);
            state.setString(2, senha);
            state.setString(3, email);
            state.setString(4, cpf);
            int linhasAfetadas = state.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println ("Os dados do Adm foram atualizados com sucesso!");
            }else{
                System.out.println ("Não foi possivel encontrar um Adm para atualizar!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Adm [idAdm=" + idAdm + "]";
    }

}
