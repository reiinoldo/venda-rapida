package model;

import controller.dao.util.MongoDBObject;

public class Produto extends MongoDBObject {
           
    private String referencia;    
    private String codigoBarrra;    
    private String descricao;    
    private double valor;

    public String getCodigoBarrra() {
        return codigoBarrra;
    }

    public void setCodigoBarrra(String codigoBarrra) {
        this.codigoBarrra = codigoBarrra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }    
    
}
