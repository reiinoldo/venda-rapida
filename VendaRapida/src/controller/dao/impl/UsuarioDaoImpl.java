package controller.dao.impl;

import controller.dao.UsuarioDao;
import controller.dao.util.ConnectionMySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public boolean salvar(Usuario usuario) throws SQLException {
        ConnectionMySql.getConnection();

        StringBuilder str = new StringBuilder();

        str.append("INSERT INTO " + Usuario.TABELA_USUARIO + " ");
        str.append("( " + Usuario.CAMPO_LOGIN + ", ");
        str.append(Usuario.CAMPO_SENHA + ", ");
        str.append(Usuario.CAMPO_NOME + ", ");
        str.append(Usuario.CAMPO_COMISSAO + ", ");
        str.append(Usuario.CAMPO_ADMINISTRADOR + ", ");
        str.append(Usuario.CAMPO_CADASTRAPRODUTO + ", ");
        str.append(Usuario.CAMPO_VENDEPRODUTO + ") ");
        str.append("values ( ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("? )");

        PreparedStatement p = ConnectionMySql.connection.prepareStatement(str.toString());
        p.setString(1, usuario.getLogin());
        p.setString(2, usuario.getSenha());
        p.setString(3, usuario.getNome());
        p.setDouble(4, usuario.getComissao());
        p.setBoolean(5, usuario.isAdministrador());
        p.setBoolean(6, usuario.isCadastraProduto());
        p.setBoolean(7, usuario.isVendeProduto());

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return !execution;
    }
  
    @Override
    public boolean editar(Usuario usuario) throws SQLException {
        ConnectionMySql.getConnection();
        
        StringBuilder sb = new StringBuilder();
        sb.append("update "+Usuario.TABELA_USUARIO+" ");
        sb.append("set ");
        sb.append(Usuario.CAMPO_SENHA+  " = ?, ");
        sb.append(Usuario.CAMPO_NOME +  " = ?, ");
        sb.append(Usuario.CAMPO_COMISSAO + " = ?, ");
        sb.append(Usuario.CAMPO_ADMINISTRADOR + " = ?, ");
        sb.append(Usuario.CAMPO_CADASTRAPRODUTO + " = ?, ");
        sb.append(Usuario.CAMPO_VENDEPRODUTO + " = ?, ");
        sb.append("where ");
        sb.append(Usuario.CAMPO_LOGIN +  " = ?");
        
        PreparedStatement pr = ConnectionMySql.connection.prepareStatement(sb.toString());
        
        pr.setString(1, usuario.getSenha());
        pr.setString(2, usuario.getNome());
        pr.setDouble(3, usuario.getComissao());
        pr.setBoolean(4, usuario.isAdministrador());
        pr.setBoolean(5, usuario.isCadastraProduto());
        pr.setBoolean(6, usuario.isVendeProduto());
        pr.setString(7, usuario.getLogin());
        
        boolean execution = pr.execute();
        ConnectionMySql.closeConnection();

        return execution;
    }

    @Override
    public boolean excluir(String login) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("delete from " + Usuario.TABELA_USUARIO + " where " + Usuario.CAMPO_LOGIN + " = ?");
        p.setString(1, login);

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return execution;
    }

    @Override
    public Usuario buscar(String login) throws Exception {
        ConnectionMySql.getConnection();
        
        PreparedStatement p = ConnectionMySql.connection.prepareStatement("select * from " + Usuario.TABELA_USUARIO + " where " + Usuario.CAMPO_LOGIN + " = ?");
        p.setString(1, login);
        ResultSet r = p.executeQuery();

        Usuario u = null; 
        if (r.next()) {
            u = new Usuario();
            u.setLogin(r.getString(Usuario.CAMPO_LOGIN));
            u.setAdministrador(r.getBoolean(Usuario.CAMPO_ADMINISTRADOR));
            u.setCadastraProduto(r.getBoolean(Usuario.CAMPO_CADASTRAPRODUTO));
            u.setComissao(r.getDouble(Usuario.CAMPO_COMISSAO));
            u.setNome(r.getString(Usuario.CAMPO_NOME));
            u.setSenha(r.getString(Usuario.CAMPO_SENHA));
            u.setVendeProduto(r.getBoolean(Usuario.CAMPO_VENDEPRODUTO));
        }
        ConnectionMySql.closeConnection();
        return u;
    }
}
