package controller.dao;

import java.util.List;
import model.Fornecedor;

public interface FornecedorDao {
     public boolean salvar(Fornecedor fornecedor) throws Exception;
     public boolean excluir(int id) throws Exception;
     public Fornecedor buscar(int id) throws Exception;
     public List<Fornecedor> listar() throws Exception;
     public List<Fornecedor> listar(Fornecedor fornecedor) throws Exception;
     public boolean editar(Fornecedor fornecedor) throws Exception;
}
