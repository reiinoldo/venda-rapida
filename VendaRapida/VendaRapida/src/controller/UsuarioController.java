package controller;

import java.util.List;
import model.Usuario;

public interface UsuarioController {

    void editar(Usuario usuario, String confirmarSenha) throws Exception;

    Usuario efetuarLogin(String login, String senha) throws Exception;

    void excluir(String login) throws Exception;

    void salvar(Usuario usuario, String confirmarSenha) throws Exception;
    
    Usuario buscar(String login) throws Exception;
    
    List<Usuario> listar() throws Exception;

    List<Usuario> listar(Usuario usuario) throws Exception;
    
}
