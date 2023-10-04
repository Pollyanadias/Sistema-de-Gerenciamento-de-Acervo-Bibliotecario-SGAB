import java.sql.Connection;
import java.sql.PreparedStatement;

public class Cliente extends Usuario {
    private int idCliente;

    public Cliente(int idCliente){
        this.idCliente = idCliente;
    }

    public Cliente(String cpf, String nome, String senha, String email, int idCliente) {
        super(cpf, nome, senha, email);
        this.idCliente = idCliente;
    }

    public void insereCliente(Usuario usuario){
        usuario.insereUsuario();
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
