package controller.impl;

import controller.FornecedorController;
import controller.dao.FornecedorDao;
import controller.dao.impl.FornecedorDaoImpl;
import java.util.List;
import model.Fornecedor;

public class FornecedorControllerImpl implements FornecedorController {
    
    public FornecedorDao fornecedorDao;
    
    public FornecedorControllerImpl() {
        fornecedorDao = new FornecedorDaoImpl();
    }
    
    private void verificarCampos(Fornecedor fornecedor) throws RegraNegocioException {
        if (fornecedor.getId() <= 0)
            throw new RegraNegocioException("Identificador não informado");
        if (fornecedor.getNome().trim().equals(""))
            throw new RegraNegocioException("Nome não informado");
        if (fornecedor.getCpfCnpj().trim().equals(""))
            throw new RegraNegocioException("Número do CPF/CNPJ não informado");
        if (fornecedor.getEndereco().trim().equals(""))
            throw new RegraNegocioException("Endereço não informado");
    }
    
    @Override
    public void salvar(Fornecedor fornecedor) throws Exception {
        if (fornecedorDao.buscar(fornecedor.getId()) != null)
            throw new RegraNegocioException("Fornecedor com identificador " + String.valueOf(fornecedor.getId()) + " já existe\nDigite outro identificador");
        verificarCampos(fornecedor);
        fornecedorDao.salvar(fornecedor);
    }
    
    @Override
    public void editar(Fornecedor fornecedor) throws Exception {
        if (fornecedorDao.buscar(fornecedor.getId()) == null)
            throw new RegraNegocioException("Fornecedor não cadastrado");
        verificarCampos(fornecedor);
        fornecedorDao.editar(fornecedor);
    }
    
    @Override
    public void excluir(int id) throws Exception{
        if (fornecedorDao.buscar(id) == null)
            throw new RegraNegocioException("Fornecedor não cadastrado");
        fornecedorDao.excluir(id);
    }
    
    @Override
    public Fornecedor buscar (int id) throws Exception {
        Fornecedor fornecedor =  fornecedorDao.buscar(id);
        if (fornecedor == null)
            throw new RegraNegocioException("Fornecedor não encontrado");
        return fornecedor;
    }
    
    @Override
    public List<Fornecedor> listar() throws Exception {
        return fornecedorDao.listar();
    }
    
    @Override
    public List<Fornecedor> listar(Fornecedor fornecedor) throws Exception {
        return fornecedorDao.listar(fornecedor);
    }

    @Override
    public int incrementar() throws Exception {
        return fornecedorDao.incrementar();
    }
}
