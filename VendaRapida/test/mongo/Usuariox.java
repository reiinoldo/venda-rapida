package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Usuariox {

    private String login;
    private String senha;
    private String nome;
    private double comissao;
    private boolean administrador;
    private boolean cadastraProduto;
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

    public BasicDBObject getBasicDBObject() {
        BasicDBObject obj = new BasicDBObject();

        obj.put("login", login);
        obj.put("senha", senha);
        obj.put("nome", nome);
        obj.put("comissao", comissao);
        obj.put("cadastraProduto", cadastraProduto);
        obj.put("administrador", administrador);
        obj.put("vendeProduto", vendeProduto);

        return obj;
    }

    public void convertDBObjectToObject(DBObject object) {
        this.administrador = (Boolean) object.get("administrador");
        this.cadastraProduto = (Boolean)  object.get("cadastraProduto");
        this.comissao = (Double) object.get("comissao");
        this.login = (String) object.get("login");
        this.nome = (String) object.get("nome");
        this.senha = (String) object.get("senha");
        this.vendeProduto = (Boolean) object.get("vendeProduto");
    }
}