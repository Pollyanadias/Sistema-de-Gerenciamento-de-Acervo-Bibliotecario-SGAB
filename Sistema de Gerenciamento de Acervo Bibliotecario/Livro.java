import java.sql.*;
import java.util.Scanner;

public class Livro {
    private int idLivro;
    private String titulo;
    private String genero;
    private String autor;
    private String dataDaPublicacao;
    private String edicao;
    private String editora;
    private String isbn;
    private boolean livroAcervo;
    private boolean livroDisponivel;


    public Livro(int idLivro, String titulo, String genero, String autor, String dataDaPublicacao, String edicao,
            String editora, String isbn, boolean livroAcervo, boolean livroDisponivel) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.dataDaPublicacao = dataDaPublicacao;
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

    public String getDataDaPublicacao() {
        return dataDaPublicacao;
    }

    public void setDataDaPublicacao(String dataDaPuplicacao) {
        this.dataDaPublicacao = dataDaPuplicacao;
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
                return new Livro(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6), result.getString(7), result.getString(8),
                        result.getBoolean(9), result.getBoolean(10));
            }
        } catch (Exception e) {//se der erro, mostre qual foi
            System.out.println(e);
        }
        return null;
    }

    public static Livro BuscaLivro(Scanner sc) {
        String tipo, busca;
        System.out.println("Por que parametro deseja buscar o livro:\n1-Titulo;\n2-Autor;\n3-Genero;\n0-Voltar;");
        int op = sc.nextInt();
        sc.nextLine();
        switch (op) {//escolhendo o tipo de busca, que será usada na pesquisa posteriormente
            case 1:
                tipo = "titulo";
                System.out.print("Titulo: ");
                busca = sc.nextLine(); //recebendo o que queremos buscar
                break;
            case 2:
                tipo = "autor";
                System.out.print("Autor: ");
                busca = sc.nextLine();
                break;
            case 3:
                tipo = "genero";
                System.out.print("Genero: ");
                busca = sc.nextLine();
                break;
            case 0: // sair
            default:
                return null;
        }

        try (Connection connection = PostgreSQLConnection.getInstance().getConnection()) {

            String query = "Select * from livro where "+ tipo +" = ?"; // Busca no banco de dados, neste caso, já que o ? é substituido por
            PreparedStatement state = connection.prepareStatement(query); // 'algo', usamos a varivavel diretamente para a pesquisa ficar correta
            state.setString(1, busca);
            ResultSet result = state.executeQuery();//Resultados da execução da query.

            while (result.next()) { 
                return new Livro(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getBoolean(9), result.getBoolean(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Livro [idLivro=" + idLivro + ", titulo=" + titulo + ", genero=" + genero + ", autor=" + autor
                + ", dataDaPublicacao=" + dataDaPublicacao + ", edicao=" + edicao + ", editora=" + editora + ", isbn="
                + isbn + ", livroAcervo=" + livroAcervo + ", livroDisponivel=" + livroDisponivel + "]";
    }

}
