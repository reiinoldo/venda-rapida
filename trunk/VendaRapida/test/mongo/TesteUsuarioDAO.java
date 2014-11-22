/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mongo;

import controller.dao.impl.UsuarioDaoImpl;
import model.Usuario;

/**
 *
 * @author Milena
 */
public class TesteUsuarioDAO {

    public static void main(String[] args) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setAdministrador(true);
        usuario.setCadastraProduto(true);
        usuario.setComissao(0);
        usuario.setLogin("admin");
        usuario.setNome("Administrador");
        usuario.setSenha("admin");
        usuario.setVendeProduto(true);
        
        Usuario usuario2 = new Usuario();
        usuario2.setLogin(null);
//        usuario2.setAdministrador(false);
//        usuario2.setCadastraProduto(true);
//        usuario2.setComissao(0);
//        usuario2.setLogin("mmgs");
//        usuario2.setNome("Maicon Machado Gerardi da Silva");
//        usuario2.setSenha("mmgs");
//        usuario2.setVendeProduto(true);

        UsuarioDaoImpl usu = new UsuarioDaoImpl();
//        usu.excluir(usuario);
        usu.salvar(usuario);
        
        
        usu.listar();
        
        
    }
}
