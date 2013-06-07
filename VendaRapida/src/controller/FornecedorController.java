package controller;

import controller.dao.FornecedorDao;
import controller.dao.impl.FornecedorDaoImpl;
import java.util.List;
import model.Fornecedor;

public class FornecedorController {
    
    public FornecedorDao fornecedorDao;
    
    public FornecedorController() {
        fornecedorDao = new FornecedorDaoImpl();
    }
    
    private void verificarCampos(Fornecedor fornecedor) throws RegraNegocioException {
        if (fornecedor.getId() <= 0)
            throw new RegraNegocioException("Identificador não informado");
        if (fornecedor.getNome().trim().equals(""))
            throw new RegraNegocioException("Nome não informado");
        if (fornecedor.getCpfCnpj().trim().equals(""))
            throw new RegraNegocioException("Número do documento não informado");
        if (fornecedor.getEndereco().trim().equals(""))
            throw new RegraNegocioException("Endereço não informado");
    }
    
    public void salvar(Fornecedor fornecedor) throws Exception {
        if (fornecedorDao.buscar(fornecedor.getId()) != null)
            throw new RegraNegocioException("Fornecedor com identificador " + String.valueOf(fornecedor.getId()) + " já existe\nDigite outro identificador");
        verificarCampos(fornecedor);
        fornecedorDao.salvar(fornecedor);
    }
    
    public void editar(Fornecedor fornecedor) throws Exception {
        if (fornecedorDao.buscar(fornecedor.getId()) == null)
            throw new RegraNegocioException("Fornecedor não cadastrado");
        verificarCampos(fornecedor);
        fornecedorDao.editar(fornecedor);
    }
    
    public void excluir(int id) throws Exception{
        if (fornecedorDao.buscar(id) == null)
            throw new RegraNegocioException("Fornecedor não cadastrado");
        fornecedorDao.excluir(id);
    }
    
    public Fornecedor buscar (int id) throws Exception {
        Fornecedor fornecedor =  fornecedorDao.buscar(id);
        if (fornecedor == null)
            throw new RegraNegocioException("Fornecedor não encontrado");
        return fornecedor;
    }
    
    public List<Fornecedor> listar() throws Exception {
        return fornecedorDao.listar();
    }
    
    public List<Fornecedor> listar(Fornecedor fornecedor) throws Exception {
        return fornecedorDao.listar(fornecedor);
    }
}
