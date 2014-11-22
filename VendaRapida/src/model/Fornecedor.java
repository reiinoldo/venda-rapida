package model;

import controller.dao.util.MongoDBObject;
import controller.dao.util.StringUtil;

public class Fornecedor extends MongoDBObject {

    private int id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String telefone;
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
