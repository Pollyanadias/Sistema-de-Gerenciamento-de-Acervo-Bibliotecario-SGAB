import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String query = "Delete From emprestimo where idLivro = ?"; 
            PreparedStatement state = connection.prepareStatement(query); 
            state.setInt(1, idLivro);
            state.executeQuery(); 
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
    }
}
