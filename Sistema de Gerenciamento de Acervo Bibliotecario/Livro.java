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

    @Override
    public String toString() {
        return "Livro [idLivro=" + idLivro + ", titulo=" + titulo + ", isbn=" + isbn + ", autor=" + autor
                + ", dataDaPuplicacao=" + dataDaPuplicacao + ", editora=" + editora + ", genero=" + genero + ", edicao="
                + edicao + ", livroDeAcervo=" + livroDeAcervo + ", dataDeEmprestimo=" + dataDeEmprestimo
                + ", empretadoPara=" + empretadoPara + "]";
    }

}
