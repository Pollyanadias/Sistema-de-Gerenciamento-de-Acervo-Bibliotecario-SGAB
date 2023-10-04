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

    public void InserirAdm(String cpf){    
        try(Connection connection = PostgreSQLConnection.getInstance().getConnection()){
        String query = "INSERT INTO Adm (cpf) VALUES (?)";
        PreparedStatement state = connection.prepareStatement(query);
        state.setString(1, cpf);
        state.executeUpdate();  

    }
    catch(Exception e){
        System.out.println(e);
    
        }
     }

    public static Adm BuscarAdm(int id){
        Adm adm = null;
        try(Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
        String query = "SELECT from Adm where id = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, id);
        ResultSet result = state.executeQuery();  
        while (result.next()) {
            return new Adm(result.getInt(1), Usuario.buscaUsuario(result.getString(2)));
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        return adm;
    }
   
    public static void ExcluirAdm(String cpf){
        try(Connection connection = PostgreSQLConnection.getInstance().getConnection() ) {
        String query = "Delete From Adm where cpf = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setString(1, cpf);
        state.executeUpdate();
        Usuario.excluirConta(cpf);
        } catch (Exception e) {
            System.out.print(e);
            
        }
     }

    @Override
    public String toString() {
        return "Adm [idAdm=" + idAdm + "]";
    }

}
