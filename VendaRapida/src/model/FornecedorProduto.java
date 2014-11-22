package model;

import controller.dao.util.MongoDBObject;

public class FornecedorProduto extends MongoDBObject {

    private int idFornecedor;
    private String referenciaProduto;

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getReferenciaProduto() {
        return referenciaProduto;
    }

    public void setReferenciaProduto(String referenciaProduto) {
        this.referenciaProduto = referenciaProduto;
    }
}
