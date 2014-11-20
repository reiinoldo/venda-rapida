package controller.impl;

import VO.ItemVO;
import VO.VendaVO;
import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import controller.dao.Dao;
import controller.dao.DaoFactory;
import controller.dao.TipoDao;
import controller.dao.impl.DaoFactoryImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Cliente;
import model.Item;
import model.Produto;
import model.Venda;
import net.sf.jasperreports.engine.JRException;

public class VendaControllerImpl implements VendaController {

    private Dao vendaDao;
    private Dao itemDao;

    public VendaControllerImpl() {
        DaoFactory daoFactory = new DaoFactoryImpl();
        vendaDao = daoFactory.CriarDao(TipoDao.VENDA);
        itemDao = daoFactory.CriarDao(TipoDao.ITEM);
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
        if (venda.getItems().isEmpty()) {
            throw new RegraNegocioException("Impossível efetuar uma venda sem itens.");
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
        Item item = new Item();
        for (int i = 0; i < lista.size(); i++) {
            item.setCodigoVenda(lista.get(i).getCodigoVenda());
            lista.get(i).setItems(itemDao.listar(item, null));
        }
        return lista;
    }

    @Override
    public void salvar(Venda venda) throws Exception {
        verificarCampos(venda);
        vendaDao.salvar(venda);

        //Salvar os ítens da venda
        for (int i = 0; i < venda.getItems().size(); i++) {
            itemDao.salvar(venda.getItems().get(i));
        }
    }

    @Override
    public Venda buscar(int codigoVenda) throws Exception {
        Venda venda = new Venda();
        venda.setCodigoVenda(codigoVenda);
        venda = (Venda) vendaDao.buscar(venda);
        if (venda == null) {
            throw new RegraNegocioException("Venda não encontrada");
        }
        Item item = new Item();
        item.setCodigoVenda(venda.getCodigoVenda());
        venda.setItems(itemDao.listar(item, null));
        return venda;
    }

    @Override
    public List<Venda> listar() throws Exception {
        return vendaDao.listar();
    }

    @Override
    public List<Venda> listar(Venda venda, Date dataFinal, Double valorInicial, Double valorFinal) throws Exception {
        verificarCamposListagem(venda, dataFinal, valorInicial, valorFinal);
        Venda vendaFinal = new Venda();
        vendaFinal.setDataVenda(dataFinal);
        List<Venda> listaVendas = vendaDao.listar(venda, vendaFinal);
        listaVendas = adicionarItensNaListaDeVendas(listaVendas);

        // filtra por valor

        List<Venda> listaVendasAux = new ArrayList<Venda>();
        for (Venda vend : listaVendas) {
            /*double valorSemDesconto = 0;
             for (Item item : vend.getItems()) {
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
    public List getListaVendaComItens(List<Venda> listaVenda) throws Exception {
        List<VendaVO> listaVendaComItens = new ArrayList<VendaVO>();
        for (Venda venda : listaVenda) {
            List<ItemVO> itensVO = new ArrayList<ItemVO>();
            for (Item item : venda.getItems()) {
                ProdutoController produtoController = new ProdutoControllerImpl();
                Produto produto = null;
                try {
                    produto = produtoController.buscar(item.getReferenciaProduto());
                } catch (Exception ex) {
                    throw new Exception(ex);
                }
                ItemVO itemVO = new ItemVO(item.getReferenciaProduto(), item.getQuantidade(), item.getValor(), produto.getDescricao());
                itensVO.add(itemVO);
            }
            ClienteController clienteController = new ClienteControllerImpl();
            Cliente cli = new Cliente();
            try {
                cli.setId(venda.getIdCliente());
                cli = clienteController.buscar(cli.getId());
            } catch (Exception ex) {
                throw new JRException(ex);
            }

            VendaVO vendaVO = new VendaVO(venda.getCodigoVenda(), venda.getIdCliente(), cli.getNome(), venda.getLoginUsuario(), venda.getDataVenda(), venda.getDesconto(), venda.getValor(), itensVO);
            listaVendaComItens.add(vendaVO);
        }
        return listaVendaComItens;
    }
}
