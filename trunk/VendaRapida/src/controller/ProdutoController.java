package controller;

import controller.dao.ProdutoDao;
import controller.dao.impl.ProdutoDaoImpl;
import java.util.List;
import model.Produto;

public class ProdutoController {
    
    public ProdutoDao produtoDao;
    
    public ProdutoController() {
        produtoDao = new ProdutoDaoImpl();
    }
    
    private void verificarCampos(Produto produto) throws RegraNegocioException {
        if (produto.getReferencia().trim().equals(""))
            throw new RegraNegocioException("Referência não informada");
        if (produto.getDescricao().trim().equals(""))
            throw new RegraNegocioException("Descrição não informada");
        if (produto.getCodigoBarrra().trim().equals(""))
            throw new RegraNegocioException("Código de barras não informado");
        if (produto.getValor() < 0.01)
            throw new RegraNegocioException("Valor não informado");
    }
    
    public void salvar(Produto produto) throws Exception {
        if (produtoDao.buscar(produto.getReferencia()) != null)
            throw new RegraNegocioException("Produto com referência " + produto.getReferencia() + " já existe\nDigite outra referência");
        verificarCampos(produto);
        produtoDao.salvar(produto);
    }
    
    public void editar(Produto produto) throws Exception {
        if (produtoDao.buscar(produto.getReferencia()) == null)
            throw new RegraNegocioException("Produto não cadastrado");
        verificarCampos(produto);
        produtoDao.editar(produto);
    }
    
    public void excluir(String referencia) throws Exception{
        if (produtoDao.buscar(referencia) == null)
            throw new RegraNegocioException("Produto não cadastrado");
        produtoDao.excluir(referencia);
    }
    
    public Produto buscar(String referencia) throws Exception {
        Produto produto = produtoDao.buscar(referencia);
        if (produto == null)
            throw new RegraNegocioException("Produto não encontrado");
        return produto;
    }
    
    public List<Produto> listar() throws Exception {
        return produtoDao.listar();
    }
    
    public List<Produto> listar(Produto produto, double valorFinal) throws Exception {
        if (produto.getValor() > valorFinal)
            throw new RegraNegocioException("Valor inicial maior que o valor final");
        return produtoDao.listar(produto, valorFinal);
    }
}
