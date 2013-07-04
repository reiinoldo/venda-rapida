package controller.impl;

import controller.ProdutoController;
import controller.dao.Dao;
import controller.dao.DaoFactory;
import controller.dao.TipoDao;
import controller.dao.impl.DaoFactoryImpl;
import java.util.List;
import model.Item;
import model.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ProdutoControllerImpl implements ProdutoController {

    private Dao produtoDao;
    private Dao itemDao;

    public ProdutoControllerImpl() {
        DaoFactory daoFactory = new DaoFactoryImpl();
        produtoDao = daoFactory.CriarDao(TipoDao.PRODUTO);
        itemDao = daoFactory.CriarDao(TipoDao.ITEM);
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
        if (produtoDao.buscar(produto) != null) {
            throw new RegraNegocioException("Produto com referência " + produto.getReferencia() + " já existe\nDigite outra referência");
        }
        verificarCampos(produto);
        produtoDao.salvar(produto);
    }

    @Override
    public void editar(Produto produto) throws Exception {
        if (produtoDao.buscar(produto) == null) {
            throw new RegraNegocioException("Produto não cadastrado");
        }
        verificarCampos(produto);
        produtoDao.editar(produto);
    }

    @Override
    public void excluir(String referencia) throws Exception {
        Produto produto = new Produto();
        produto.setReferencia(referencia);
        if (produtoDao.buscar(produto) == null) {
            throw new RegraNegocioException("Produto não cadastrado");
        }
        Item item = new Item();
        item.setReferenciaProduto(referencia);
        if (itemDao.buscar(item) != null) {
            throw new RegraNegocioException("Produto está associado a uma venda, não sendo possível excluí-lo");
        }
        produtoDao.excluir(produto);
    }

    @Override
    public Produto buscar(String referencia) throws Exception {
        Produto produto = new Produto();
        produto.setReferencia(referencia);
        return (Produto)produtoDao.buscar(produto);
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
        Produto produtoFinal = new Produto();
        produtoFinal.setValor(valorFinal);
        return produtoDao.listar(produto, produtoFinal);
    }

    @Override
    public void gerarRelatorio(List listaGerada, String path) throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/relatorios/relProdutos.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaGerada));
        JasperExportManager.exportReportToPdfFile(print, path);
    }

    @Override
    public Produto buscarCodigoBarras(String codigoBarras) throws Exception {
        /*Produto produto = produtoDao.buscarCodigoBarras(codigoBarras);
        return produto;*/
        List<Produto> lista = this.listar();
        for (int i =0; i < lista.size(); i++) {
            if (lista.get(i).getCodigoBarrra().equals(codigoBarras))
                return lista.get(i);
        }
        return null;
    }
}
