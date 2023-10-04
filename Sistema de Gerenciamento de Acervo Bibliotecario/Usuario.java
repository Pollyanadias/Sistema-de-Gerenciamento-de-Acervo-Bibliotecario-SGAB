import java.sql.*;
import java.util.ArrayList;;

public class Usuario {
    private String cpf;
    private String nome;
    private String senha;
    private String email;
    private String[] telefone = new String[2];

    public Usuario(){}
    public Usuario(String cpf, String nome, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    /**
     * Um metodo que insere uma instância de usuario no banco de dados
     */
    public void insereUsuario(){
        try(Connection connection = PostgreSQLConnection.getInstance().getConnection()){
        String query = "INSERT into usuario (cpf, nome, senha, email) VALLUES (?, ?, ?, ?)";
        PreparedStatement state = connection.prepareStatement(query);
        state.setString(1, cpf);
        state.setString(2, nome);
        state.setString(3, senha);
        state.setString(4, email);
        state.executeUpdate();
        }catch (Exception e){
            System.out.println (e);
        }
    }

    /*
     * ele recebe um usuario e retorna uma instacia dele
     */
    public static Usuario buscaUsuario(String cpf){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "Select * From usuario where cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf);
            ResultSet result = state.executeQuery();

            while (result.next()){
                return new Usuario(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
            }
        }catch (Exception e){
            System.out.println (e);
        }
        return null;
    }

    /*
     * vai remover um usuario do banco de dados com base em seu cpf
     */
    public static void removeUsuario(String cpf){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "DELETE From usuario where cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.executeQuery();
        }catch(Exception e){
            System.out.println (e);
        }
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + ", email=" + email + "]";
    }

}
