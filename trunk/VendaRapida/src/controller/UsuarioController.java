package controller;

import model.Usuario;

public interface UsuarioController {

    void editar(Usuario usuario, String confirmarSenha) throws Exception;

    Usuario efetuarLogin(String login, String senha) throws Exception;

    void excluir(String login, String senha) throws Exception;

    void salvar(Usuario usuario, String confirmarSenha) throws Exception;
    
}
