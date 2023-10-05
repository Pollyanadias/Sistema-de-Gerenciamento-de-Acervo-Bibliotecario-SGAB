import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Cliente extends Usuario {
    private int idCliente;    

    public Cliente(String cpf, String nome, String senha, String email, ArrayList<String> telefone, int idCliente) {
        super(cpf, nome, senha, email, telefone);
        this.idCliente = idCliente;
    }

    public void insereCliente(Usuario usuario){
        usuario.criarConta();
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "INSERT INTO Cliente";
            PreparedStatement state = connection.prepareStatement(query);
            state.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

     public static Cliente BuscaCliente(String cpf){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "SELECT * from cliente where cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf);
            ResultSet result = state.executeQuery();

            while (result.next()) {
                return new Cliente(result.getInt(1), Usuario.buscaUsuario(result.getString(2)));
            }

        } catch (Exception e) {
            System.out.println(e);    
        }
        return null;
    }

    public static void removeCliente(String cpf){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "Delete from Cliente where cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.executeQuery();
            Usuario.excluirConta(cpf);
        } catch (Exception e) {
            System.out.println(e); 
        }
    }

    public static Usuario registroCliente(String nome, String email, String senha, String cpf) {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
           
            String query = "SELECT * FROM usuario WHERE email = ? OR cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, email);
            state.setString(2, cpf);
            ResultSet result = state.executeQuery();
        
            if (result.next()) {
                //um usuario com o mesmo cpf ou email já existem
                System.out.println("um usuario com um mesmo cpf ou email já existem");
    
                // modificação dos dados do usuário que já existem
                String updateQuery = "UPDATE usuario SET nome = ?, senha = ? WHERE email = ? OR cpf = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, nome);
                updateStatement.setString(2, senha);
                updateStatement.setString(3, email);
                updateStatement.setString(4, cpf);
                updateStatement.executeUpdate();
    
                // retorna um usuario modificado
                return new Usuario(cpf, nome, email, senha);
            }
    
            // caso não tenha um usuario cm o msm nome ou cpf, continua o registro
            String insertQuery = "INSERT INTO usuario (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, cpf);
            insertStatement.setString(2, nome);
            insertStatement.setString(3, email);
            insertStatement.setString(4, senha);
            insertStatement.executeUpdate();
    
            // registro ok, retorna um novo usuario 
            return new Usuario(cpf, nome, email, senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + "]";
    }

}
