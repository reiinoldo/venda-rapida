package controller.impl;

import controller.FornecedorController;
import controller.dao.Dao;
import controller.dao.impl.FornecedorDaoImpl;
import java.util.List;
import model.Fornecedor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class FornecedorControllerImpl implements FornecedorController {
    
    public Dao fornecedorDao;
    
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
        if (fornecedorDao.buscar(fornecedor) != null)
            throw new RegraNegocioException("Fornecedor com identificador " + String.valueOf(fornecedor.getId()) + " já existe\nDigite outro identificador");
        verificarCampos(fornecedor);
        fornecedorDao.salvar(fornecedor);
    }
    
    @Override
    public void editar(Fornecedor fornecedor) throws Exception {
        if (fornecedorDao.buscar(fornecedor) == null)
            throw new RegraNegocioException("Fornecedor não cadastrado");
        verificarCampos(fornecedor);
        fornecedorDao.editar(fornecedor);
    }
    
    @Override
    public void excluir(int id) throws Exception{
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        if (fornecedorDao.buscar(fornecedor) == null)
            throw new RegraNegocioException("Fornecedor não cadastrado");
        fornecedorDao.excluir(fornecedor);
    }
    
    @Override
    public Fornecedor buscar (int id) throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        return (Fornecedor)fornecedorDao.buscar(fornecedor);        
    }
    
    @Override
    public List<Fornecedor> listar() throws Exception {
        return fornecedorDao.listar();
    }
    
    @Override
    public List<Fornecedor> listar(Fornecedor fornecedor) throws Exception {
        return fornecedorDao.listar(fornecedor, null);
    }

    @Override
    public int incrementar() throws Exception {
        return fornecedorDao.incrementar();
    }

    @Override
    public void gerarRelatorio(List listaGerada, String path) throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/relatorios/relFornecedores.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaGerada));
        JasperExportManager.exportReportToPdfFile(print, path);
    }
}
