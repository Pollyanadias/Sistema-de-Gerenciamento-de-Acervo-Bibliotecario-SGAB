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

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + "]";
    }

}
