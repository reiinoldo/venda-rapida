package model;

public class Cliente {
    
    public static final String TABELA_CLIENTE = "vendarapida.cliente";
    
    public static final String CAMPO_ID = "cliente.id";
    private int id;
    
    public static final String CAMPO_NOME = "cliente.nome";
    private String nome;
    
    public static final String CAMPO_CPFCNPJ = "cliente.cpfcnpj";
    private String cpfCnpj;
    
    public static final String CAMPO_EMAIL = "cliente.email";
    private String email;
    
    public static final String CAMPO_TELEFONE = "cliente.telefone";
    private String telefone;
    
    public static final String CAMPO_ENDERECO = "cliente.endereco";
    private String endereco;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
