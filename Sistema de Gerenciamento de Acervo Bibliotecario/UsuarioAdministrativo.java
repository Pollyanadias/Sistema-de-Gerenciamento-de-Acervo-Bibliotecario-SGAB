public class UsuarioAdministrativo extends Usuario {
    private int idAdministrativo;

    public UsuarioAdministrativo(String nome, int cpf, String telefone, String senha, String email, String endereco,
        int idAdministrativo) {
        super(nome, cpf, telefone, senha, email, endereco);
        this.idAdministrativo = idAdministrativo;
    }

    public int getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(int idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    @Override
    public String toString() {
        return "UsuarioAdministrativo [idAdministrativo=" + idAdministrativo + "]";
    }

}
