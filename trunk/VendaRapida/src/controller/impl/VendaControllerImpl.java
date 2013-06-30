package controller.impl;

import controller.VendaController;
import controller.dao.ItemDao;
import controller.dao.VendaDao;
import controller.dao.impl.ItemDaoImpl;
import controller.dao.impl.VendaDaoImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Item;
import model.Venda;

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
            for (Item item : vend.getItems()) {
                valorSemDesconto += item.getValor();
            }
            double valorTotal = valorSemDesconto - vend.getDesconto();

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
}
