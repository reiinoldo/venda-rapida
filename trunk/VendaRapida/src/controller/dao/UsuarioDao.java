package controller.dao;

import java.util.List;
import model.Usuario;

public interface UsuarioDao {
    public boolean salvar(Usuario usuario) throws Exception;
    public Usuario buscar(String login) throws Exception;
    public boolean editar(Usuario usuario) throws Exception;
    public boolean excluir(String login) throws Exception;
    public List<Usuario> listar() throws Exception;
    public List<Usuario> listar(Usuario usuario) throws Exception;
}