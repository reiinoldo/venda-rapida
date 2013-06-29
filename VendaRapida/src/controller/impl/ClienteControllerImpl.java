package controller.impl;

import controller.ClienteController;
import controller.VendaController;
import controller.dao.ClienteDao;
import controller.dao.impl.ClienteDaoImpl;
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

    public ClienteDao clienteDao;

    public ClienteControllerImpl() {
        clienteDao = new ClienteDaoImpl();
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
        if (clienteDao.buscar(cliente.getId()) != null) {
            throw new RegraNegocioException("Cliente com identificador " + String.valueOf(cliente.getId()) + " já existe\nDigite outro identificador");
        }
        verificarCampos(cliente);
        clienteDao.salvar(cliente);
    }

    @Override
    public void editar(Cliente cliente) throws Exception {
        if (clienteDao.buscar(cliente.getId()) == null) {
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

        
        if (clienteDao.buscar(id) == null) {
            throw new RegraNegocioException("Cliente não cadastrado.");
        }
        clienteDao.excluir(id);
    }

    @Override
    public Cliente buscar(int id) throws Exception {
        return clienteDao.buscar(id);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        return clienteDao.listar();
    }

    @Override
    public List<Cliente> listar(Cliente cliente) throws Exception {
        return clienteDao.listar(cliente);
    }

    @Override
    public int incrementar() throws Exception {
        return clienteDao.incrementar();
    }
    
    @Override
    public void gerarRelatorio(List listaGerada, String path) throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/relatorios/relClientes.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaGerada));
        JasperExportManager.exportReportToPdfFile(print, path);
    }
}
