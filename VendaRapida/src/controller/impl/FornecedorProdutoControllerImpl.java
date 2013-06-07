package controller.impl;

import controller.FornecedorProdutoController;
import controller.dao.FornecedorProdutoDao;
import controller.dao.impl.FornecedorProdutoDaoImpl;
import java.util.List;
import model.Fornecedor;
import model.FornecedorProduto;
import model.Produto;

public class FornecedorProdutoControllerImpl implements FornecedorProdutoController {
    
    public FornecedorProdutoDao fornecedorProdutoDao;
    
    public FornecedorProdutoControllerImpl() {
        fornecedorProdutoDao = new FornecedorProdutoDaoImpl();
    }
    
    private void verificarCampos(FornecedorProduto fornecedorProduto) throws RegraNegocioException {
        if (fornecedorProduto.getIdFornecdor() == 0)
            throw new RegraNegocioException("Identificador do fornecedor não informado");
        if (fornecedorProduto.getReferenciaProduto().trim().equals(""))
            throw new RegraNegocioException("Referência do produto não encontrada");
    }
    
    @Override
    public void salvar(FornecedorProduto fornecedorProduto) throws Exception {
        if (fornecedorProdutoDao.buscar(fornecedorProduto.getIdFornecdor(), fornecedorProduto.getReferenciaProduto()) != null)
            throw new RegraNegocioException("Produto " + fornecedorProduto.getReferenciaProduto() + " com fornecedor " + String.valueOf(fornecedorProduto.getIdFornecdor()) + " não encontrado");
        verificarCampos(fornecedorProduto);
        fornecedorProdutoDao.salvar(fornecedorProduto);
    }
    
    @Override
    public void excluir(int idFornecedor, String referenciaProduto) throws Exception{
        if (fornecedorProdutoDao.buscar(idFornecedor, referenciaProduto) == null)
            throw new RegraNegocioException("Produto " + referenciaProduto + " com fornecedor " + String.valueOf(idFornecedor) + " não encontrado");
        fornecedorProdutoDao.excluir(idFornecedor, referenciaProduto);
    }
    
    @Override
    public FornecedorProduto buscar(int idFornecedor, String referenciaProduto) throws Exception {
        FornecedorProduto fornecedorProduto = fornecedorProdutoDao.buscar(idFornecedor, referenciaProduto);
        if (fornecedorProduto == null)
            throw new RegraNegocioException("Produto " + referenciaProduto + " com fornecedor " + String.valueOf(idFornecedor) + " não encontrado");
        return fornecedorProduto;
    }
    
    @Override
    public List<Produto> listarProdutos(int idFornecedor) throws Exception {
        return fornecedorProdutoDao.listarProdutos(idFornecedor);
    }
    
    @Override
    public List<Fornecedor> listarFornecedores(String referenciaProduto) throws Exception {
        return fornecedorProdutoDao.listarFornecedores(referenciaProduto);
    }
}
