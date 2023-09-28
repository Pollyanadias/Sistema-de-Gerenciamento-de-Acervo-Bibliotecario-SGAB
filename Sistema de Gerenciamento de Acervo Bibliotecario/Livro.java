public class Livro {
    private int idLivro;
    private String titulo;
    private String isbn;
    private String autor;
    private String dataDaPuplicacao;
    private String editora;
    private String genero;
    private String edicao;
    private boolean livroDeAcervo;
    private String dataDeEmprestimo;
    private UsuarioCliente empretadoPara;

    
    public Livro(int idLivro, String titulo, String isbn, String autor, String dataDaPuplicacao, String editora,
            String genero, String edicao, boolean livroDeAcervo, String dataDeEmprestimo,
            UsuarioCliente empretadoPara) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.dataDaPuplicacao = dataDaPuplicacao;
        this.editora = editora;
        this.genero = genero;
        this.edicao = edicao;
        this.livroDeAcervo = livroDeAcervo;
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.empretadoPara = empretadoPara;
    }

    public Livro(int idLivro, String titulo, String genero, String autor, String dataPublicacao, String edicao, String editor, String isbn) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.edicao = edicao;
        this.editor = editor;
        this.isbn = isbn;
    } //Construtor para a Busca por ID

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

    public String getDataDaPuplicacao() {
        return dataDaPuplicacao;
    }

    public void setDataDaPuplicacao(String dataDaPuplicacao) {
        this.dataDaPuplicacao = dataDaPuplicacao;
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

    public boolean isLivroDeAcervo() {
        return livroDeAcervo;
    }

    public void setLivroDeAcervo(boolean livroDeAcervo) {
        this.livroDeAcervo = livroDeAcervo;
    }

    public String getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(String dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
    }

    public UsuarioCliente getEmpretadoPara() {
        return empretadoPara;
    }

    public void setEmpretadoPara(UsuarioCliente empretadoPara) {
        this.empretadoPara = empretadoPara;
    }

    //Parte das Funções Importantes pra Caramba

    public static Livro BuscaLivroId(int id){        
        try(Connection connection = PostgreSQLConnection.getInstance().getConnection()) {//tente fazer a conexão antes de executar a busca
            String query = "Select * from livro where idLivro = ?"; //Busca no banco de dados, ? = ainda a ser preenchido.
            PreparedStatement state = connection.prepareStatement(query); //Usado para executar a query.
            state.setInt(1, id);// Preenche os pontos de interrogação com o que queremos, neste caso, o ID a ser buscado.
            ResultSet result = state.executeQuery();//Resultados da execução da query.
            while (result.next()) { //Enquanto houverem linhas de resultados da busca para serem impressas, retorna-os.
                return new Livro(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8));
            }
            else //indicação de que houve um erro.
                System.out.println("ERROOO!!");
        } catch (Exception e) { //caso não seja possível fazer a conexão, indique o motivo.
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Livro [idLivro=" + idLivro + ", titulo=" + titulo + ", isbn=" + isbn + ", autor=" + autor
                + ", dataDaPuplicacao=" + dataDaPuplicacao + ", editora=" + editora + ", genero=" + genero + ", edicao="
                + edicao + ", livroDeAcervo=" + livroDeAcervo + ", dataDeEmprestimo=" + dataDeEmprestimo
                + ", empretadoPara=" + empretadoPara + "]";
    }

}
