package model;

public class FornecedorProduto {
    
    public static final String TABELA_FORNECEDORPRODUTO = "vendarapida.fornecedorproduto";
    
    public static final String CAMPO_IDFORNECEDOR = "fornecedorproduto.idfornecedor";
    private int idFornecdor;
    
    public static final String CAMPO_REFERENCIAPRODUTO = "fornecedorproduto.referenciaproduto";
    private String referenciaProduto;

    public int getIdFornecdor() {
        return idFornecdor;
    }

    public void setIdFornecdor(int idFornecdor) {
        this.idFornecdor = idFornecdor;
    }

    public String getReferenciaProduto() {
        return referenciaProduto;
    }

    public void setReferenciaProduto(String referenciaProduto) {
        this.referenciaProduto = referenciaProduto;
    }
}
