import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Emprestimo {
    private String cpf;
    private int idLivro;
    private String dataEmprestimo;
    private String dataPrevista;
    private String dataDevolucao;
    
    public Emprestimo(String cpf, int idLivro, String dataEmprestimo, String dataPrevista, String dataDevolucao) {
        this.cpf = cpf;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.dataDevolucao = dataDevolucao;
    }

    public static Emprestimo BuscaEmprestimoCpf(int id) { 
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {//se a conexão funcionar
            String query = "Select * from emprestimo where cpf = ?"; //pesquisa
            PreparedStatement state = connection.prepareStatement(query); //execução
            state.setInt(1, id); //preenchimento
            ResultSet result = state.executeQuery(); //resultado
            while (result.next()) {// enquanto houverem respostas, imprima-as
                return new Emprestimo (result.getString(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5));
            }
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
        return null;
}


    /**
     * Cria uma lista emprestimo que irá armazenar os objetos de Emprestimo que foram 
     * criadas no banco, e depois de passar por cada um armazena na lista e retorna ela 
     * @param cpf
     * @return List<Emprestimo>
     */
    public static List<Emprestimo> ListaEmprestimoCpf(String cpf) {
    List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    Connection connection = PostgreSQLConnection.getInstance().getConnection();
    PreparedStatement state = null;
    ResultSet result = null;

    try  {
        String query = "SELECT * FROM emprestimo WHERE cpf = ?";
        state = connection.prepareStatement(query);
        state.setString(1, cpf);
        result = state.executeQuery();
        
        // Provavelmente está funcionando
        while (result.next()) {
            Emprestimo emprestimo = new Emprestimo(
                result.getString(1),
                result.getInt(2),
                result.getString(3),
                result.getString(4),
                result.getString(5)
            );
            emprestimos.add(emprestimo);
        }
        return emprestimos;
    } catch (Exception e) {
        System.out.println(e);
    }
    return null;
}


    public static Emprestimo BuscaEmprestimoId(int id) { 
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {//se a conexão funcionar
            String query = "Select * from emprestimo where idLivro = ?"; //pesquisa
            PreparedStatement state = connection.prepareStatement(query); //execução
            state.setInt(1, id); //preenchimento
            ResultSet result = state.executeQuery(); //resultado
            while (result.next()) {// enquanto houverem respostas, imprima-as
                return new Emprestimo (result.getString(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5));
            }
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
        return null;
    }

    public void inserirEmprestimo() {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "INSERT INTO Emprestimo (cpf, idLivro, dataEmprestimo, dataPrevista, dataDevolução) VALUES  (?, ?, ?, ?, ?)";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.setInt(2, idLivro);
            state.setString(3, dataEmprestimo);
            state.setString(4, dataPrevista);
            state.setString(5, dataDevolucao);
            state.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void devolverLivro(int idLivro) {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "UPDATE emprestimo SET dataDevolucao = ? WHERE idLivro = ?"; 
            PreparedStatement state = connection.prepareStatement(query); 
            state.setDate(1, Date.valueOf(LocalDate.now()));
            state.setInt(2, idLivro);
            state.executeQuery(); 
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
    }

     public List<Emprestimo> listarAtrasados() {
        List<Emprestimo> atrasados = new ArrayList<>();
        Date hoje = new Date(); // Obtém a data atual

        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM Emprestimo WHERE idDataPrevista = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, new java.sql.Date(hoje.getTime())); // Parâmetro para a data atual

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Emprestimo emprestimo = new Emprestimo(query, idLivro, query, query, query);
                        // Preencher o objeto Emprestimo com os dados do banco de dados
                        emprestimo.setCpf(cpf);
                        emprestimo.setDataEmprestimo(dataEmprestimo);
                        emprestimo.setDataPrevista(dataPrevista);

                        resultSet.getString("cpf");
                        resultSet.getInt("idEmprestado");
                        resultSet.getDate("idDataPrevista");

                        atrasados.add(emprestimo);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atrasados;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmpretimo) {
        this.dataEmprestimo = dataEmpretimo;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
