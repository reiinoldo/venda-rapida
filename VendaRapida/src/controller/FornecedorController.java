package controller;

import java.util.List;
import model.Fornecedor;
import net.sf.jasperreports.engine.JRException;

public interface FornecedorController {

    Fornecedor buscar(int id) throws Exception;

    void editar(Fornecedor fornecedor) throws Exception;

    void excluir(int id) throws Exception;

    List<Fornecedor> listar() throws Exception;

    List<Fornecedor> listar(Fornecedor fornecedor) throws Exception;

    void salvar(Fornecedor fornecedor) throws Exception;
    
    int incrementar()throws Exception;
    
    void gerarRelatorio(List listaGerada, String path) throws JRException;
    
}
