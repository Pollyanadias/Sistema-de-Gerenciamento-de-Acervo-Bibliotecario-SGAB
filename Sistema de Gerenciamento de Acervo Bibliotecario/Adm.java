public class Adm extends Usuario {
    private int idAdm;

    public Adm(int idAdm){
        this.idAdm =idAdm;
    }

    public Adm(String cpf, String nome, String senha, String email, int idAdm) {
        super(cpf, nome, senha, email);
        this.idAdm = idAdm;
    }

    public int getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(int idAdm) {
        this.idAdm = idAdm;
    }

    @Override
    public String toString() {
        return "Adm [idAdm=" + idAdm + "]";
    }

}
