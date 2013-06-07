package controller;

import controller.dao.ClienteDao;
import controller.dao.impl.ClienteDaoImpl;
import java.util.List;
import model.Cliente;


public class ClienteController {
    
    public ClienteDao clienteDao;
    
    public ClienteController() {
        clienteDao = new ClienteDaoImpl();
    }
    
    private void verificarCampos(Cliente cliente) throws RegraNegocioException {
        if (cliente.getId() <= 0)
            throw new RegraNegocioException("Identificador não informado");
        if (cliente.getNome().trim().equals(""))
            throw new RegraNegocioException("Nome não informado");
        if (cliente.getCpfCnpj().trim().equals(""))
            throw new RegraNegocioException("Número do documento não informado");
        if (cliente.getEndereco().trim().equals(""))
            throw new RegraNegocioException("Endereço não informado");
    }
    
    public void salvar(Cliente cliente) throws Exception {
        if (clienteDao.buscar(cliente.getId()) != null)
            throw new RegraNegocioException("Cliente com identificador " + String.valueOf(cliente.getId()) + " já existe\nDigite outro identificador");
        verificarCampos(cliente);
        clienteDao.salvar(cliente);
    }
    
    public void editar(Cliente cliente) throws Exception {
        if (clienteDao.buscar(cliente.getId()) == null)
            throw new RegraNegocioException("Cliente não cadastrado");
        verificarCampos(cliente);
        clienteDao.editar(cliente);
    }
    
    public void excluir(int id) throws Exception{
        if (clienteDao.buscar(id) == null)
            throw new RegraNegocioException("Cliente não cadastrado");
        clienteDao.excluir(id);
    }
    
    public Cliente buscar(int id) throws Exception {
        Cliente cliente = clienteDao.buscar(id);
        if (cliente == null)
            throw new RegraNegocioException("Cliente não encontrado");
        return cliente;
    }
    
    public List<Cliente> listar() throws Exception {
        return clienteDao.listar();
    }
    
    public List<Cliente> listar(Cliente cliente) throws Exception {
        return clienteDao.listar(cliente);
    }
}
