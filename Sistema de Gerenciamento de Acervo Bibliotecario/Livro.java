import java.sql.*;
import java.util.Scanner;

public class Livro {
    private int idLivro;
    private String titulo;
    private String genero;
    private String autor;
    private Date dataPublicacao;
    private String edicao;
    private String editora;
    private String isbn;
    private boolean livroAcervo;
    private boolean livroDisponivel;


    public Livro(int idLivro, String titulo, String genero, String autor, Date dataPublicacao, String edicao,
            String editora, String isbn, boolean livroAcervo, boolean livroDisponivel) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.edicao = edicao;
        this.editora = editora;
        this.isbn = isbn;
        this.livroAcervo = livroAcervo;
        this.livroDisponivel = livroDisponivel;
    }

    //Milhares de getters and setters

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataDaPuplicacao) {
        this.dataPublicacao = dataDaPuplicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public boolean islivroAcervo() {
        return livroAcervo;
    }

    public void setlivroAcervo(boolean livroAcervo) {
        this.livroAcervo = livroAcervo;
    }

    public boolean isLivroDisponivel() {
        return livroDisponivel;
    }

    public void setLivroDisponivel(boolean livroDisponivel) {
        this.livroDisponivel = livroDisponivel;
    }
    
    //Parte das Funções Importantes pra Caramba

    public static Livro BuscaLivroId(int id) { //Usado pelos administradores, para alteração, listagem e remoção
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {//se a conexão funcionar
            String query = "Select * from livro where idLivro = ?"; //cria a query, que é a pesquisa que iremos fazer
            PreparedStatement state = connection.prepareStatement(query); //cria o state, aquele que executa a pesquisa
            state.setInt(1, id); //preenche os ? com as informações desejadas
            ResultSet result = state.executeQuery(); //recebe a tabela com as respostas da pesquisa
            while (result.next()) {// enquanto houverem respostas, imprima-as
                return new Livro(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getString(6), result.getString(7), result.getString(8), result.getInt(9), result.getInt(10));
            }
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
        return null;
    }
    public static List<Livro> BuscaLivroTitulo(String titulo) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection(); 
        PreparedStatement state = null; //cria o state, aquele que executa a pesquisa
        ResultSet result = null;
        List<Livro> livrosEncontrados = new ArrayList<>();
    
        try {
            // Seleciona tudo (*) na tabela Livro onde o título é igual ao recebido
            String query = "SELECT * FROM Livro WHERE titulo = ?"; //cria a query, que é a pesquisa que iremos fazer
            state = connection.prepareStatement(query); 
            state.setString(1, titulo);
            result = state.executeQuery();
            
            while (result.next()) {
                // Para cada registro encontrado, cria um objeto Livro e adiciona na lista
                Livro livro = new Livro(
                    result.getInt("idLivro"),
                    result.getString("Titulo"),
                    result.getString("Genero"), 
                    result.getString("Autor"),
                    result.getString("DataPublicacao"),
                    result.getString("Edicao"),
                    result.getString("Editora"),
                    result.getString("ISBN"),
                    result.getInt("quantLivros"),
                    result.getInt("quantDisponivel")
                );
    
                livrosEncontrados.add(livro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar o result e o result2
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return livrosEncontrados;
    }   

    public static Livro BuscaLivroGenero(String busca) {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "Select * from livro where genero like ? "; // Busca no banco de dados, neste caso, já que o ? é substituido por
            PreparedStatement state = connection.prepareStatement(query); // 'algo', usamos a varivavel diretamente para a pesquisa ficar correta
            state.setString(1, "%" + busca + "%");
            ResultSet result = state.executeQuery();// Resultados da execução da query.
            
            // Enquanto houverem linhas de resultados da busca para serem impressas, retorna-os.
            while (result.next()) {  
                return new Livro(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getString(6), result.getString(7), result.getString(8), result.getInt(9), result.getInt(10));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
  
    public static Livro BuscaLivroAutor(String busca) {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {

            String query = "Select * from livro where autor like ? "; // Busca no banco de dados, neste caso, já que o ? é substituido por
            PreparedStatement state = connection.prepareStatement(query); // 'algo', usamos a varivavel diretamente para a pesquisa ficar correta
            state.setString(1, "%" + busca + "%");
            ResultSet result = state.executeQuery();// Resultados da execução da query.
            // Enquanto houverem linhas de resultados da busca para serem impressas, retorna-os.
            while (result.next()) {  
                return new Livro(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getString(6), result.getString(7), result.getString(8), result.getInt(9), result.getInt(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void inserirLivro() {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "INSERT INTO Livro (titulo, genero, autor, dataPublicacao, edicao, editora, isbn, livroAcervo, livroDisponivel) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement state = connection.prepareStatement(query);
            state.setString(1, titulo);
            state.setString(2, genero);
            state.setString(3, autor);
            state.setDate(4, dataPublicacao);
            state.setString(5, edicao);
            state.setString(6, editora);
            state.setString(7, isbn);
            state.setBoolean(8, livroAcervo);
            state.setBoolean(9, livroDisponivel);
            state.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void excluirLivro(int idLivro) {
        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {
            String query = "Delete From livro where idLivro = ?"; 
            PreparedStatement state = connection.prepareStatement(query); 
            state.setInt(1, idLivro);
            state.executeQuery(); 
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
    }
    
    @Override
    public String toString() {
        return "Livro [idLivro=" + idLivro + ", titulo=" + titulo + ", genero=" + genero + ", autor=" + autor
                + ", dataPublicacao=" + dataPublicacao + ", edicao=" + edicao + ", editora=" + editora + ", isbn="
                + isbn + ", livroAcervo=" + livroAcervo + ", livroDisponivel=" + livroDisponivel + "]";
    }

}
