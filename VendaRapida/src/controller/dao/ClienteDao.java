package controller.dao;

import java.util.List;
import model.Cliente;

public interface ClienteDao {
     public boolean salvar(Cliente cliente) throws Exception;
     public boolean excluir(int id) throws Exception;
     public Cliente buscar(int id) throws Exception;
     public List<Cliente> listar() throws Exception;
     public List<Cliente> listar(Cliente cliente) throws Exception;
     public boolean editar(Cliente cliente) throws Exception;
     public int incrementar() throws Exception;
}