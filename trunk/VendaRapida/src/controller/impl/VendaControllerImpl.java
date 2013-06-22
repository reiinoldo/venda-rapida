package controller.impl;

import controller.VendaController;
import controller.dao.ItemDao;
import controller.dao.VendaDao;
import controller.dao.impl.ItemDaoImpl;
import controller.dao.impl.VendaDaoImpl;
import java.util.Date;
import java.util.List;
import model.Venda;

public class VendaControllerImpl implements VendaController {
    
    public VendaDao vendaDao;
    
    public VendaControllerImpl() {
        vendaDao = new VendaDaoImpl();
    }
    
    private void verificarCampos(Venda venda) throws RegraNegocioException {
        if (venda.getCodigoVenda() == 0)
            throw new RegraNegocioException("Código não informado");
        /*if (venda.getCodigoPagSeguro().trim().equals(""))
            throw new RegraNegocioException("Código da venda no PagSeguro não informado");*/
        if (venda.getDataVenda() == null)
            throw new RegraNegocioException("Data da venda não informada");
        if (venda.getIdCliente() <= 0)
            throw new RegraNegocioException("Cliente não informado");
        if (venda.getItems() == null)
            throw new RegraNegocioException("Impossível efetuar uma venda sem ítens");
        if (venda.getLoginUsuario().trim().equals(""))
            throw new RegraNegocioException("Vendedor não informado");
        if (venda.getValor() < 0.01)
            throw new RegraNegocioException("Valor não informado");
    }
    
    private void verificarCamposListagem(Venda venda, Date dataFinal, Double valorInicial, Double valorFinal) throws RegraNegocioException {
        if (valorInicial > valorFinal)
            throw new RegraNegocioException("Valor inicial maior que o valor final");
        if (dataFinal.before(venda.getDataVenda()))
            throw new RegraNegocioException("Data inicial maior que a data final");
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
        if (vendaDao.buscar(venda.getCodigoVenda()) != null)
            throw new RegraNegocioException("Venda com código " + String.valueOf(venda.getCodigoVenda()) + " já existe\nDigite outro código");
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
        if (venda == null)
            throw new RegraNegocioException("Venda não encontrada");
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
        return adicionarItensNaListaDeVendas(vendaDao.listar(venda, dataFinal, valorInicial, valorFinal));
    }
}