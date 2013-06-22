package model;

import controller.dao.util.StringUtil;

public class Fornecedor {
    
    public static final String TABELA_FORNECEDOR = "vendarapida.fornecedor";
    
    public static final String CAMPO_ID = "fornecedor.id";
    private int id;
    
    public static final String CAMPO_NOME = "fornecedor.nome";
    private String nome;
    
    public static final String CAMPO_CPFCNPJ = "fornecedor.cpfcnpj";
    private String cpfCnpj;
    
    public static final String CAMPO_EMAIL = "fornecedor.email";
    private String email;
    
    public static final String CAMPO_TELEFONE = "fornecedor.telefone";
    private String telefone;
    
    public static final String CAMPO_ENDERECO = "fornecedor.endereco";
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
        this.cpfCnpj = StringUtil.returnDigito(cpfCnpj);
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
        this.telefone = StringUtil.returnDigito(telefone);
    }  
    
    
}
