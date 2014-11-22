package model;

import controller.dao.util.MongoDBObject;

public class Item extends MongoDBObject{    
    private int codigoVenda;
    private String referenciaProduto;
    private int quantidade;
    private double valor;
    
    private Produto produto;

    public Item() {
    }

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.referenciaProduto = produto.getReferencia();
        this.valor = produto.getValor();
        this.quantidade = quantidade;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public String getReferenciaProduto() {
        return referenciaProduto;
    }

    public void setReferenciaProduto(String referenciaProduto) {
        this.referenciaProduto = referenciaProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}