import java.sql.*;
import java.util.ArrayList;
public class Usuario {

    private String cpf;
    private String nome;
    private String senha;
    private String email;
    private ArrayList<String> telefone;

    public Usuario(String cpf, String nome, String senha, String email, ArrayList<String> telefone) {
        setCpf(cpf);
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        setTelefone(telefone);
    }

    /**
     * Método criarConta: Responsável por criar uma nova conta de Usuário no banco de dados sistema.
     * Obs.: Recebe uma instancia da Classe Usuario com dados já formatados corretamente,a falta 
     * dessa formatação pode causar erros.
     */
    public void criarConta(){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            
            // Checa se o usuário com esse cpf já existe no sistema. Se for null não existe.
            if(buscaUsuario(cpf) == null){
                // Insere o usuário na tabela Usuario
                String query = "INSERT Into usuario (cpf, nome, senha, email) VALUES (?, ?, ?, ?)"; 
                PreparedStatement state = connection.prepareStatement(query);
                state.setString(1, cpf);
                state.setString(2, nome);
                state.setString(3, senha);
                state.setString(4, email);
                state.executeUpdate();

                // Insere os telefones dele na tabela Telefone com base em seu cpf
                for (int i = 0; i < telefone.size(); i++) {
                    if(telefone.get(i) != null){
                        query = "INSERT Into telefone (cpf, numero) VALUES (?, ?)"; 
                        state = connection.prepareStatement(query);
                        state.setString(1, cpf);
                        state.setString(2, telefone.get(i));
                        state.executeUpdate();
                    }
                }
            }
            else
                System.out.println("ERRO! Este usuário já está cadastrado no sistema!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Método excluirConta: Método que recebe um cpf de um usário e remove ele do banco de dados do sistema.
     * Obs.: O Método não trata dados, portanto o cpf deve ser recebido no formato correto.
     * @param cpf
     */
    public static void excluirConta(String cpf){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            
            // Remove o usuário da tabela Usuario
            String query = "DELETE From usuario where cpf = ?"; 
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf); 
            state.executeQuery();

            // Remove os telefones endereçados ao usuário removido
            query = "DELETE From telefone where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf); 
            state.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Método buscaUsuario: Responsável por retornar um Usuário do banco de dados de acordo com seu cpf.
     * Obs.: O Método não trata dados, portanto o cpf deve ser recebido no formato correto. Retorna
     * null caso não encontre.
     * @param cpf
     * @return Usuario
     */
    public static Usuario buscaUsuario(String cpf){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            
            // Busca o usuário na tabela usuario usando o cpf
            String query = "SELECT * From usuario where cpf = ?"; 
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf); 
            ResultSet result = state.executeQuery();
            
            // Usando o mesmo cpf, ele busca os telefones, deixando-os organizados em ordem crescente
            ArrayList<String> telefone = new ArrayList<String>();
            query = "SELECT numero FROM telefone WHERE cpf = ? ORDER BY idTelefone";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            ResultSet result2 = state.executeQuery();

            // Aplica cada tupla obtida com esse cpf em um ArrayList de String para telefone
            while(result2.next()){
                telefone.add(result2.getString(3));
            }

            // Retorna o usuário
            return new Usuario(result.getString(1), result.getString(2), result.getString(3), result.getString(4), telefone);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Usuario confirmaCredencial(String email, String senha ){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "SELECT cpf From usuario where email = ? AND senha = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, email);
            state.setString(2, senha);
            ResultSet result = state.executeQuery();
            return buscaUsuario(result.getString(1));
        } catch (Exception e) {
            System.out.println (e);
        }

        return null;

    }
    
    public void editarUsuario(String cpf, String nome, String senha, String email, String telefone1, String telefone2, String telefone3 ){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "UPDATE Usuario SET nome = ?, senha = ?, email = ? WHERE cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, nome);
            state.setString(2, senha);
            state.setString(3, email);
            state.setString(4, cpf);
            int linhasAfetadas = state.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println ("Os dados do usuário foram atualizados com sucesso!");
            }else{
                System.out.println ("Não foi possivel encontrar um usuário para atualizar!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarTelefones(String cpf, String telefone1, String telefone2, String telefone3 ){
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()){
            String query = "UPDATE telefone SET telefone1 = ?, telefone2 = ?, telefone3 = ? WHERE cpf = ?";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, telefone1);
            state.setString(2, telefone2);
            state.setString(3, telefone3);
            state.setString(4, cpf);
            int linhasAfetadas = state.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println ("Os dados do usuário foram atualizados com sucesso!");
            }else{
                System.out.println ("Não foi possivel encontrar um usuário para atualizar!");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public void setTelefone(ArrayList<String> telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Usuario [cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", telefone="
                + telefone + "]";
    }
}
