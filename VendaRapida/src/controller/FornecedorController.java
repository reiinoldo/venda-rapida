package controller;

import java.util.List;
import model.Fornecedor;

public interface FornecedorController {

    Fornecedor buscar(int id) throws Exception;

    void editar(Fornecedor fornecedor) throws Exception;

    void excluir(int id) throws Exception;

    List<Fornecedor> listar() throws Exception;

    List<Fornecedor> listar(Fornecedor fornecedor) throws Exception;

    void salvar(Fornecedor fornecedor) throws Exception;
    
}
