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
            return new Usuario(result.getString(1), result.getString(2), result.getString(3), result.getString(4));

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Usuario loginUsuario(String email, String senha) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            // Remove o usuário da tabela Usuario
            String query = "SELECT cpf From usuario where email = ? AND senha = ?";
            state = connection.prepareStatement(query);
            state.setString(1, email);
            state.setString(2, senha);
            result = state.executeQuery();

            if(result.next()){
                return buscaUsuario(result.getString(1));
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void editarUsuario(String nome, String senha, String email) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;

        // Primeiro checa se algum desses dados foi recebido e aplica valores aos que
        // forem null.
        setNome(nome != null ? nome : getNome());
        setSenha(senha != null ? senha : getSenha());
        setEmail(email != null ? email : getEmail());

        try {

            // Atualiza nome, senha e email na tabela usuario na posição do cpf usado.
            String query = "UPDATE Usuario SET nome = ?, senha = ?, email = ? WHERE cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, this.nome);
            state.setString(2, this.senha);
            state.setString(3, this.email);
            state.setString(4, this.cpf);
            state.executeUpdate();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * Método listaUsuario: Método que acessa o banco de dados e retorna um
     * ArrayList de Usuario.
     * 
     * @return ArrayList<Usuario>
     */
    public static ArrayList<Usuario> listaUsuario() {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        ResultSet result, result2;

        // ArrayList do tipo Usuario, que será retornado com todos os usuarios do banco.
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try {

            // Seleciona todos os usuarios.
            String query = "SELECT * From Usuario";
            state = connection.prepareStatement(query);
            result = state.executeQuery();

            while (result.next()) {

                // Seleciona os telefones desses usuarios.
                result2 = buscaTelefone(result.getString(1));
                ArrayList<String> telefone = new ArrayList<String>();
                while (result2.next()) {
                    telefone.add(result2.getString(2));
                }

                // Cria um objeto para cada um e coloca no ArrayList.
                Usuario user = new Usuario(result.getString(1), result.getString(2), result.getString(3), result.getString(4), telefone);
                usuarios.add(user);
            }

            state.close();
            return usuarios;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * A partir daqui temos declarações de seters e geters. os Seters vão funcionar
     * pra formatar os dados,
     * para que erros não ocorram no momento de mexer com o Banco de Dados. Os
     * Geters estão como auxiliares,
     * sendo usados por métodos internos e externos (da Classe Herdeira).
     */

    public void setCpf(String cpf) {
        if (cpf != null)
            this.cpf = cpf;
        else
            System.out.println("O valor de cpf não pode ser null");
    }

    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else
            System.out.println("O valor de nome não pode ser null");
    }

    public void setEmail(String email) {
        if (email != null)
            this.email = email;
        else
            System.out.println("O valor de email não pode ser null");
    }

    public void setSenha(String senha) {
        if (senha != null)
            this.senha = senha;
        else
            System.out.println("O valor de senha não pode ser null");
    }

    public void setTelefone(ArrayList<String> telefone) {
        if (telefone != null)
            this.telefone = telefone;
        else
            System.out.println("O valor de telefone não pode ser null");
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Usuario [cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", telefone="
                + telefone + "]";
    }

}
