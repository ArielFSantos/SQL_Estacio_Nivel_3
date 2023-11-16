
package cadastrobd.model;

/**
 *
 * @author ariel
 */
public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica() {
        // Construtor padrão
    }

    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }

    /**
     *
     * @return
     */
    @Override
    public String getNome() {
        return super.getNome();
}

    /**
     *
     * @return
     */
    @Override
    public String getLogradouro() {
        return super.getLogradouro();
}


    @Override
    public String getCidade() {
        return super.getCidade();
}

    /**
     *
     * @return
     */
    @Override
    public String getEstado() {
        return super.getEstado();
}


    @Override
   public String getTelefone() {
        return super.getTelefone();
}

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    /**
     *
     * @param novoNome
     */
    @Override
    public void setNome(String novoNome) {
        super.setNome(novoNome);
    }

    public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
}

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }


}