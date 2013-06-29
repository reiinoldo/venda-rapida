package controller.impl;

import controller.ProdutoController;
import controller.dao.ItemDao;
import controller.dao.ProdutoDao;
import controller.dao.impl.ItemDaoImpl;
import controller.dao.impl.ProdutoDaoImpl;
import java.util.List;
import model.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ProdutoControllerImpl implements ProdutoController {

    public ProdutoDao produtoDao;
    public ItemDao itemDao;

    public ProdutoControllerImpl() {
        produtoDao = new ProdutoDaoImpl();
        itemDao = new ItemDaoImpl();
    }

    private void verificarCampos(Produto produto) throws RegraNegocioException {
        if (produto.getReferencia().trim().equals("")) {
            throw new RegraNegocioException("Referência não informada");
        }
        if (produto.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Descrição não informada");
        }
        if (produto.getCodigoBarrra().trim().equals("")) {
            throw new RegraNegocioException("Código de barras não informado");
        }
        if (produto.getValor() < 0.01) {
            throw new RegraNegocioException("Valor não informado");
        }
    }

    @Override
    public void salvar(Produto produto) throws Exception {
        if (produtoDao.buscar(produto.getReferencia()) != null) {
            throw new RegraNegocioException("Produto com referência " + produto.getReferencia() + " já existe\nDigite outra referência");
        }
        verificarCampos(produto);
        produtoDao.salvar(produto);
    }

    @Override
    public void editar(Produto produto) throws Exception {
        if (produtoDao.buscar(produto.getReferencia()) == null) {
            throw new RegraNegocioException("Produto não cadastrado");
        }
        verificarCampos(produto);
        produtoDao.editar(produto);
    }

    @Override
    public void excluir(String referencia) throws Exception {
        if (itemDao.buscarItemPorProduto(referencia) != null) {
            throw new RegraNegocioException("Produto não cadastrado");
        }
        produtoDao.excluir(referencia);
    }

    @Override
    public Produto buscar(String referencia) throws Exception {
        return produtoDao.buscar(referencia);
    }

    @Override
    public List<Produto> listar() throws Exception {
        return produtoDao.listar();
    }

    @Override
    public List<Produto> listar(Produto produto, double valorFinal) throws Exception {
        if (produto.getValor() > valorFinal) {
            throw new RegraNegocioException("Valor inicial maior que o valor final");
        }
        return produtoDao.listar(produto, valorFinal);
    }

    @Override
    public void gerarRelatorio(List listaGerada, String path) throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/relatorios/relProdutos.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaGerada));
        JasperExportManager.exportReportToPdfFile(print, path);
    }

    @Override
    public Produto buscarCodigoBarras(String codigoBarras) throws Exception {
        Produto produto = produtoDao.buscarCodigoBarras(codigoBarras);
        return produto;
    }
}
