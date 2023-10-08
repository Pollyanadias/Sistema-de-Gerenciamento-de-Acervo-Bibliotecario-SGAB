import java.sql.*;
import java.util.ArrayList;
public class Usuario {

    private String cpf;
    private String nome;
    private String senha;
    private String email;
    private ArrayList<String> telefone;

    public Usuario(String cpf, String nome, String senha, String email) {
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
     /**
     * buscaTelefone é um método auxiliar. Busca um telefone no banco com base no
     * seu cpf, e retorna um
     * ResultSet contendo o idTelefone e numero em seu primeiro e segundo slot.
     * 
     * @param cpf
     * @return ResultSet
     */
    public static ResultSet buscaTelefone(String cpf) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            // Seleciona idTelefone e numero, ordenados por ordem crescente de id, da tabela
            // telefone.
            String query = "SELECT idTelefone, numero FROM telefone WHERE cpf = ? ORDER BY idTelefone";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);

            /*
             * O ResultSet é semelhante a um ArrayList, e o acesso aos dados dele ocorre de
             * maneira
             * semelhante a um iterrator.
             */
            result = state.executeQuery();
            return (result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método editaTelefone: Responsável por editar o telefone na tabela de uma
     * instâcia da classe Usuario.
     * Obs.: Recebe dados já formatados.
     * 
     * @param telefone
     */
    public void editaTelefone(ArrayList<String> telefone) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        ResultSet result = null;

        // Esse ArrayList serve pra receber os ids ta tabela telefone, referentes ao cpf
        // do usuario.
        ArrayList<Integer> tele = new ArrayList<Integer>();

        // Se estiver vazio, para evitar erros aplico o próprio telefone.
        setTelefone(telefone != null ? telefone : getTelefone());

        try {

            // Essa busca vai me retornar um ResultSet com idTelefone e numero.
            result = buscaTelefone(this.cpf);
            while (result.next()) {
                // Repare que estou acessando a coluna 1, que contém os ids.
                tele.add(result.getInt(1));
            }

            // Com base nos ids, faço a atualização do telefone
            for (int i = 0; i < tele.size(); i++) {
                String query = "UPDATE Telefone SET numero = ? WHERE idTelefone = ?";
                PreparedStatement state = connection.prepareStatement(query);
                state.setString(1, telefone.get(i));
                state.setInt(2, tele.get(i));
                state.executeUpdate();
                state.close();
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
