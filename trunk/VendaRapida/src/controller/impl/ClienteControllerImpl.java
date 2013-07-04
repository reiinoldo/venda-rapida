package controller.impl;

import controller.ClienteController;
import controller.VendaController;
import controller.dao.Dao;
import controller.dao.DaoFactory;
import controller.dao.TipoDao;
import controller.dao.impl.DaoFactoryImpl;
import java.util.List;
import model.Cliente;
import model.Venda;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ClienteControllerImpl implements ClienteController {

    private Dao clienteDao;

    public ClienteControllerImpl() {
        DaoFactory daoFactory = new DaoFactoryImpl();
        clienteDao = daoFactory.CriarDao(TipoDao.CLIENTE);
    }

    private void verificarCampos(Cliente cliente) throws RegraNegocioException {
        if (cliente.getId() <= 0) {
            throw new RegraNegocioException("Identificador não informado");
        }
        if (cliente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome não informado");
        }
        if (cliente.getCpfCnpj().trim().equals("")) {
            throw new RegraNegocioException("Número do documento não informado");
        }
        if (cliente.getEndereco().trim().equals("")) {
            throw new RegraNegocioException("Endereço não informado");
        }
    }

    @Override
    public void salvar(Cliente cliente) throws Exception {
        if (clienteDao.buscar(cliente) != null) {
            throw new RegraNegocioException("Cliente com identificador " + String.valueOf(cliente.getId()) + " já existe\nDigite outro identificador");
        }
        verificarCampos(cliente);
        clienteDao.salvar(cliente);
    }

    @Override
    public void editar(Cliente cliente) throws Exception {
        if (clienteDao.buscar(cliente) == null) {
            throw new RegraNegocioException("Cliente não cadastrado");
        }
        verificarCampos(cliente);
        clienteDao.editar(cliente);
    }

    @Override
    public void excluir(int id) throws Exception {
        VendaController vendaController = new VendaControllerImpl();
        Venda venda = new Venda();
        venda.setIdCliente(id);
        List<Venda> listaVenda = vendaController.listar(venda, null, null, null);

        if (!listaVenda.isEmpty()) 
            throw new RegraNegocioException("Cliente já efetuou compras e não pode ser excluído.");

        
        Cliente cliente = new Cliente();
        cliente.setId(id);
        if (clienteDao.buscar(cliente) == null) {
            throw new RegraNegocioException("Cliente não cadastrado.");
        }
        clienteDao.excluir(cliente);
    }

    @Override
    public Cliente buscar(int id) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return (Cliente)clienteDao.buscar(cliente);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        return clienteDao.listar();
    }

    @Override
    public List<Cliente> listar(Cliente cliente) throws Exception {
        return clienteDao.listar(cliente, null);
    }

    @Override
    public int incrementar() throws Exception {
        return clienteDao.incrementar();
    }
}
