package controller.dao;

import java.util.List;
import model.Fornecedor;
import model.FornecedorProduto;
import model.Produto;

public interface FornecedorProdutoDao {
     public boolean salvar(FornecedorProduto fornecedorProduto) throws Exception;
     public boolean excluir(int idFornecedor, String referenciaProduto) throws Exception;
     public FornecedorProduto buscar(int idFornecedor, String referenciaProduto) throws Exception;
     public List<Produto> listarProdutos(int idFornecedor) throws Exception;
     public List<Fornecedor> listarFornecedores (String referenciaProduto) throws Exception;
}