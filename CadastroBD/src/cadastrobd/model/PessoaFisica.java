package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica() {
        // Construtor padrão
    }

    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void setNome(String nome) {
    this.nome = nome;
}

    @Override
    public int getId() {
        return id;
    }


    @Override
    public String getNome() {
    return nome;
}


    @Override
    public String getLogradouro() {
        return logradouro;
    }


    @Override
    public String getCidade() {
        return cidade;
    }

    @Override
    public String getEstado() {
        return estado;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public void setEmail(String email) {
    this.email = email;
}

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
