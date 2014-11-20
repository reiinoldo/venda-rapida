package model;

public class Produto {
    
    public static final String TABELA_PRODUTO = "vendarapida.produto";
    
    public static final String CAMPO_REFERENCIA = "produto.referencia";
    private String referencia;
    
    public static final String CAMPO_CODIGOBARRA = "produto.codigobarra";
    private String codigoBarrra;
    
    public static final String CAMPO_DESCRICAO = "produto.descricao";
    private String descricao;
    
    public static final String CAMPO_VALOR = "produto.valor";
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
