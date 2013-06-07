package model;

public class Usuario {
    
    public static final String TABELA_USUARIO = "vendarapida.usuario";
    
    public static final String CAMPO_LOGIN = "usuario.login";
    private String login;
    
    public static final String CAMPO_SENHA = "usuario.senha";
    private String senha;
    
    public static final String CAMPO_NOME = "usuario.nome";
    private String nome;
    
    public static final String CAMPO_COMISSAO = "usuario.comissao";
    private double comissao;
    
    public static final String CAMPO_ADMINISTRADOR = "usuario.administrador";
    private boolean administrador;
    
    public static final String CAMPO_CADASTRAPRODUTO = "usuario.cadastraproduto";
    private boolean cadastraProduto;
    
    public static final String CAMPO_VENDEPRODUTO = "usuario.vendeproduto";
    private boolean vendeProduto;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isCadastraProduto() {
        return cadastraProduto;
    }

    public void setCadastraProduto(boolean cadastraProduto) {
        this.cadastraProduto = cadastraProduto;
    }

    public boolean isVendeProduto() {
        return vendeProduto;
    }

    public void setVendeProduto(boolean vendeProduto) {
        this.vendeProduto = vendeProduto;
    }


}