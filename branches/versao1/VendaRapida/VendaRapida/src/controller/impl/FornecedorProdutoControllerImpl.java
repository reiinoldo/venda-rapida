package controller.impl;

import controller.FornecedorController;
import controller.FornecedorProdutoController;
import controller.ProdutoController;
import controller.dao.Dao;
import controller.dao.DaoFactory;
import controller.dao.TipoDao;
import controller.dao.impl.DaoFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.FornecedorProduto;
import model.Produto;

public class FornecedorProdutoControllerImpl implements FornecedorProdutoController {
    
    private Dao fornecedorProdutoDao;
    
    public FornecedorProdutoControllerImpl() {
        DaoFactory daoFactory = new DaoFactoryImpl();
        fornecedorProdutoDao = daoFactory.CriarDao(TipoDao.FORNECEDORPRODUTO);
    }
    
    private void verificarCampos(FornecedorProduto fornecedorProduto) throws RegraNegocioException {
        if (fornecedorProduto.getIdFornecdor() == 0)
            throw new RegraNegocioException("Identificador do fornecedor não informado");
        if (fornecedorProduto.getReferenciaProduto().trim().equals(""))
            throw new RegraNegocioException("Referência do produto não encontrada");
    }
    
    @Override
    public void salvar(FornecedorProduto fornecedorProduto) throws Exception {
        if (fornecedorProdutoDao.buscar(fornecedorProduto) != null)
            throw new RegraNegocioException("Produto " + fornecedorProduto.getReferenciaProduto() + " com fornecedor " + String.valueOf(fornecedorProduto.getIdFornecdor()) + " não encontrado");
        verificarCampos(fornecedorProduto);
        fornecedorProdutoDao.salvar(fornecedorProduto);
    }
    
    @Override
    public void excluir(int idFornecedor, String referenciaProduto) throws Exception{
        FornecedorProduto fornecedorProduto = new FornecedorProduto();
        fornecedorProduto.setIdFornecdor(idFornecedor);
        fornecedorProduto.setReferenciaProduto(referenciaProduto);
        if (fornecedorProdutoDao.buscar(fornecedorProduto) == null)
            throw new RegraNegocioException("Produto " + referenciaProduto + " com fornecedor " + String.valueOf(idFornecedor) + " não encontrado");
        fornecedorProdutoDao.excluir(fornecedorProduto);
    }
    
    @Override
    public FornecedorProduto buscar(int idFornecedor, String referenciaProduto) throws Exception {
        FornecedorProduto fornecedorProduto = new FornecedorProduto();
        fornecedorProduto.setIdFornecdor(idFornecedor);
        fornecedorProduto.setReferenciaProduto(referenciaProduto);
        fornecedorProduto = (FornecedorProduto)fornecedorProdutoDao.buscar(fornecedorProduto);
        if (fornecedorProduto == null)
            throw new RegraNegocioException("Produto " + referenciaProduto + " com fornecedor " + String.valueOf(idFornecedor) + " não encontrado");
        return fornecedorProduto;
    }
    
    @Override
    public List<Produto> listarProdutos(int idFornecedor) throws Exception {
        FornecedorProduto fornecedorProduto = new FornecedorProduto();
        fornecedorProduto.setIdFornecdor(idFornecedor);
        fornecedorProduto.setReferenciaProduto(null); //Garantir que não trará resultado errado
        List<FornecedorProduto> listaFPs = fornecedorProdutoDao.listar(fornecedorProduto, null);
        List<Produto> listaProdutos = new ArrayList<Produto>();
        ProdutoController produtoController = new ProdutoControllerImpl();
        Produto produto;
        for (int i = 0; i < listaFPs.size(); i++) {
            produto = produtoController.buscar(listaFPs.get(i).getReferenciaProduto());
            listaProdutos.add(produto);
        }
        return listaProdutos;
    }
    
    @Override
    public List<Fornecedor> listarFornecedores(String referenciaProduto) throws Exception {
        FornecedorProduto fornecedorProduto = new FornecedorProduto();
        fornecedorProduto.setIdFornecdor(0); //Garantir que não trará resultado errado
        fornecedorProduto.setReferenciaProduto(referenciaProduto);
        List<FornecedorProduto> listaFPs = fornecedorProdutoDao.listar(fornecedorProduto, null);
        List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        FornecedorController fornecedorController = new FornecedorControllerImpl();
        Fornecedor fornecedor;
        for (int i = 0; i < listaFPs.size(); i++) {
            fornecedor = fornecedorController.buscar(listaFPs.get(i).getIdFornecdor());
            listaFornecedores.add(fornecedor);
        }
        return listaFornecedores;
    }

    @Override
    public void excluirFornecedoresDoProduto(String referenciaProduto) throws Exception {
        List<Fornecedor> lista = this.listarFornecedores(referenciaProduto);
        FornecedorProduto fornecedorProduto = new FornecedorProduto();
        for (int i = 0; i < lista.size(); i++) {
            fornecedorProduto.setIdFornecdor(lista.get(i).getId());
            fornecedorProduto.setReferenciaProduto(referenciaProduto);
            fornecedorProdutoDao.excluir(fornecedorProduto);
        }
    }
}
