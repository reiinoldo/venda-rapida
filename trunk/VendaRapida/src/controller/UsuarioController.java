package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import model.Usuario;

public class UsuarioController {

    public UsuarioDao usuarioDao;
    
    public UsuarioController() {
        usuarioDao = new UsuarioDaoImpl();
    }
    
    private boolean confirmarSenha(String senha, String senha2) {
        return senha.equals(senha2);
    }
    
    private void verificarCampos(Usuario usuario, String confirmarSenha) throws RegraNegocioException {
        if (usuario.getLogin().trim().equals(""))
            throw new RegraNegocioException("Login não informado");
        if (usuario.getSenha().trim().equals(""))
            throw new RegraNegocioException("Senha não informada");
        if (!confirmarSenha(usuario.getSenha(), confirmarSenha))
            throw new RegraNegocioException("Senhas não conferem.");
        if (usuario.getNome().trim().equals(""))
            throw new RegraNegocioException("Nome não informado");
    }
    
    public Usuario efetuarLogin(String login, String senha) throws Exception {
        Usuario usuario = usuarioDao.buscar(login);
        if (usuario == null)
            throw new RegraNegocioException("Usuário não cadastrado");
        if (!confirmarSenha(usuario.getSenha(), senha))
            throw new RegraNegocioException("Senha incorreta");
        return usuario;
    }
    
    public void salvar(Usuario usuario, String confirmarSenha) throws Exception {
        if (usuarioDao.buscar(usuario.getLogin()) != null)
            throw new RegraNegocioException("Usuário já existe. Digite outro login");
        verificarCampos(usuario, confirmarSenha);
        usuarioDao.salvar(usuario);
    }
    
    public void editar(Usuario usuario, String confirmarSenha) throws Exception {
        if (usuarioDao.buscar(usuario.getLogin()) == null)
            throw new RegraNegocioException("Usuário não cadastrado");
        verificarCampos(usuario, confirmarSenha);
        usuarioDao.editar(usuario);
    }
    
    public void excluir(String login, String senha) throws Exception{
        
        //Falta colocar alguma regra que impeça de excluir o cliente
        
        Usuario usuario = usuarioDao.buscar(login);
        if (usuario == null)
            throw new RegraNegocioException("Usuário não cadastrado");
        if (!confirmarSenha(usuario.getSenha(), senha))
            throw new RegraNegocioException("Senha incorreta");
        usuarioDao.excluir(login);
    }
}
