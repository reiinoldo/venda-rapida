package controller.impl;

import VO.ItemVO;
import VO.VendaVO;
import controller.VendaController;
import controller.dao.DAO;
import controller.dao.ItemDao;
import controller.dao.ProdutoDao;
import controller.dao.VendaDao;
import controller.dao.impl.ClienteDaoImpl;
import controller.dao.impl.ItemDaoImpl;
import controller.dao.impl.ProdutoDaoImpl;
import controller.dao.impl.VendaDaoImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Cliente;
import model.Item;
import model.Produto;
import model.Venda;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class VendaControllerImpl implements VendaController {

    public VendaDao vendaDao;

    public VendaControllerImpl() {
        vendaDao = new VendaDaoImpl();
    }

    private void verificarCampos(Venda venda) throws RegraNegocioException {
        if (venda.getCodigoVenda() == 0) {
            throw new RegraNegocioException("Código não informado");
        }
        /*if (venda.getCodigoPagSeguro().trim().equals(""))
         throw new RegraNegocioException("Código da venda no PagSeguro não informado");*/
        if (venda.getDataVenda() == null) {
            throw new RegraNegocioException("Data da venda não informada");
        }
        if (venda.getIdCliente() <= 0) {
            throw new RegraNegocioException("Cliente não informado");
        }
        if (venda.getItems() == null) {
            throw new RegraNegocioException("Impossível efetuar uma venda sem ítens");
        }
        if (venda.getLoginUsuario().trim().equals("")) {
            throw new RegraNegocioException("Vendedor não informado");
        }
    }

    private void verificarCamposListagem(Venda venda, Date dataFinal, Double valorInicial, Double valorFinal) throws RegraNegocioException {
        if ((dataFinal != null) && (valorInicial != null) && (valorFinal != null)) {
            if (valorInicial > valorFinal) {
                throw new RegraNegocioException("Valor inicial maior que o valor final");
            }
            if ((venda.getDataVenda() != null) && (dataFinal.before(venda.getDataVenda()))) {
                throw new RegraNegocioException("Data inicial maior que a data final");
            }
        }
    }

    private List<Venda> adicionarItensNaListaDeVendas(List<Venda> lista) throws Exception {
        ItemDao itemVenda = new ItemDaoImpl();
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).setItems(itemVenda.listar(lista.get(i).getCodigoVenda()));
        }
        return lista;
    }

    @Override
    public void salvar(Venda venda) throws Exception {
        verificarCampos(venda);
        vendaDao.salvar(venda);

        //Salvar os ítens da venda
        ItemDao itemDao = new ItemDaoImpl();
        for (int i = 0; i < venda.getItems().size(); i++) {
            itemDao.salvar(venda.getItems().get(i));
        }
    }

    @Override
    public Venda buscar(int codigoVenda) throws Exception {
        Venda venda = vendaDao.buscar(codigoVenda);
        if (venda == null) {
            throw new RegraNegocioException("Venda não encontrada");
        }
        ItemDao itemDao = new ItemDaoImpl();
        venda.setItems(itemDao.listar(codigoVenda));
        return venda;
    }

    @Override
    public List<Venda> listar() throws Exception {
        return vendaDao.listar();
    }

    @Override
    public List<Venda> listar(Venda venda, Date dataFinal, Double valorInicial, Double valorFinal) throws Exception {
        verificarCamposListagem(venda, dataFinal, valorInicial, valorFinal);
        List<Venda> listaVendas = vendaDao.listar(venda, dataFinal);
        listaVendas = adicionarItensNaListaDeVendas(listaVendas);

        // filtra por valor

        List<Venda> listaVendasAux = new ArrayList<Venda>();
        for (Venda vend : listaVendas) {
            double valorSemDesconto = 0;
            /*for (Item item : vend.getItems()) {
                valorSemDesconto += item.getValor();
            }
            double valorTotal = valorSemDesconto - vend.getDesconto();*/
            double valorTotal = vend.getValorTotalComDesconto();

            //if (valorTotal >= valorFinal || valorTotal <= valorInicial) {
            if (valorTotal <= valorFinal && valorTotal >= valorInicial) {
                listaVendasAux.add(vend);
            }
        }

        return listaVendasAux;
    }

    @Override
    public int incrementar() throws Exception {
        return vendaDao.incrementar();
    }

    @Override
    public void gerarRelatorio(List listaGerada, String path, boolean comItens) throws JRException {
        if (comItens) {
            List listaVenda = new ArrayList();
            for (Venda venda : (List<Venda>) listaGerada) {
                List<ItemVO> itensVO = new ArrayList<ItemVO>();
                for (Item item : venda.getItems()) {
                    ProdutoDao produtoDao = new ProdutoDaoImpl();
                    Produto produto = null;
                    try {
                        produto = produtoDao.buscar(item.getReferenciaProduto());
                    } catch (Exception ex) {
                        throw new JRException(ex);
                    }
                    ItemVO itemVO = new ItemVO(item.getReferenciaProduto(), item.getQuantidade(), item.getValor(), produto.getDescricao());
                    itensVO.add(itemVO);
                }
                DAO clienteDao = new ClienteDaoImpl();
                Cliente cli = new Cliente();
                try {
                    cli.setId(venda.getIdCliente());
                    cli = (Cliente)clienteDao.buscar(cli.getId());
                } catch (Exception ex) {
                    throw new JRException(ex);
                }
                
                VendaVO vendaVO = new VendaVO(venda.getCodigoVenda(), venda.getIdCliente(), cli.getNome(), venda.getLoginUsuario(), venda.getDataVenda(), venda.getDesconto(), venda.getValor(), itensVO);
                listaVenda.add(vendaVO);
            }
            JasperReport report = JasperCompileManager.compileReport("src/relatorios/relVendasComItens.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaVenda));
            JasperExportManager.exportReportToPdfFile(print, path);
        } else {
            JasperReport report = JasperCompileManager.compileReport("src/relatorios/relVendas.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaGerada));
            JasperExportManager.exportReportToPdfFile(print, path);
        }
    }
}
