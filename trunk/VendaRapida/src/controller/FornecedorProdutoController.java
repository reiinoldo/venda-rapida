package controller;

import java.util.List;
import model.Fornecedor;
import model.FornecedorProduto;
import model.Produto;

public interface FornecedorProdutoController {

    FornecedorProduto buscar(int idFornecedor, String referenciaProduto) throws Exception;

    void excluir(int idFornecedor, String referenciaProduto) throws Exception;
    
    public boolean excluirFornecedoresDoProduto(String referenciaProduto) throws Exception;

    List<Fornecedor> listarFornecedores(String referenciaProduto) throws Exception;

    List<Produto> listarProdutos(int idFornecedor) throws Exception;

    void salvar(FornecedorProduto fornecedorProduto) throws Exception;
    
}
