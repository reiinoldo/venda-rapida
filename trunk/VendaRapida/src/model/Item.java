package model;

public class Item {
    
    public static final String TABELA_ITEM = "vendarapida.item";
    
    public static final String CAMPO_CODIGOVENDA = "item.codigovenda";
    private int codigoVenda;
    
    public static final String CAMPO_REFERENCIAPRODUTO = "item.referenciaproduto";
    private String referenciaProduto;
    
    public static final String CAMPO_QUANTIDADE = "item.quantidade";
    private int quantidade;
    
    public static final String CAMPO_VALOR = "item.valor";
    private double valor;

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
    
}
