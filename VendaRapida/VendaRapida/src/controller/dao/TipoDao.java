package controller.dao;

/**
 *
 * @author andrebampi
 */
public enum TipoDao {
    CLIENTE("Cliente"),
    FORNECEDOR("Fornecedor"),
    FORNECEDORPRODUTO("FornecedorProduto"),
    ITEM("Item"),
    PRODUTO("Produto"),
    USUARIO("Usuario"),
    VENDA("Venda");
    
    private String tipo;
    
    private TipoDao(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public static TipoDao getTipoDao(int numero){
        int idx = 0;
        for (TipoDao t : TipoDao.values()){
            if(idx == numero){
                return t;
            }
            idx++;
        }
        return null;
    }
    
}
