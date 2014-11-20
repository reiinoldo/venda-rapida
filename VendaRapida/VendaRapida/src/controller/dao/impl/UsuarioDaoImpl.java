package controller.dao.impl;

import controller.dao.Dao;
import controller.dao.util.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDaoImpl implements Dao<Usuario> {

    @Override
    public boolean salvar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

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

            PreparedStatement p = conexao.prepareStatement(str.toString());
            p.setString(1, usuario.getLogin());
            p.setString(2, usuario.getSenha());
            p.setString(3, usuario.getNome());
            p.setDouble(4, usuario.getComissao());
            p.setBoolean(5, usuario.isAdministrador());
            p.setBoolean(6, usuario.isCadastraProduto());
            p.setBoolean(7, usuario.isVendeProduto());

            boolean execution = p.execute();

            return !execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }
  
    @Override
    public boolean editar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            StringBuilder sb = new StringBuilder();
            sb.append("update "+Usuario.TABELA_USUARIO+" ");
            sb.append("set ");
            sb.append(Usuario.CAMPO_SENHA+  " = ?, ");
            sb.append(Usuario.CAMPO_NOME +  " = ?, ");
            sb.append(Usuario.CAMPO_COMISSAO + " = ?, ");
            sb.append(Usuario.CAMPO_ADMINISTRADOR + " = ?, ");
            sb.append(Usuario.CAMPO_CADASTRAPRODUTO + " = ?, ");
            sb.append(Usuario.CAMPO_VENDEPRODUTO + " = ? ");
            sb.append(" where ");
            sb.append(Usuario.CAMPO_LOGIN +  " = ?");

            PreparedStatement pr = conexao.prepareStatement(sb.toString());

            pr.setString(1, usuario.getSenha());
            pr.setString(2, usuario.getNome());
            pr.setDouble(3, usuario.getComissao());
            pr.setBoolean(4, usuario.isAdministrador());
            pr.setBoolean(5, usuario.isCadastraProduto());
            pr.setBoolean(6, usuario.isVendeProduto());
            pr.setString(7, usuario.getLogin());

            boolean execution = pr.execute();

            return execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Usuario usuario) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("delete from " + Usuario.TABELA_USUARIO + " where " + Usuario.CAMPO_LOGIN + " = ?");
            p.setString(1, usuario.getLogin());

            boolean execution = p.execute();

            return execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public Usuario buscar(Usuario usuario) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("select * from " + Usuario.TABELA_USUARIO + " where " + Usuario.CAMPO_LOGIN + " = ?");
            p.setString(1, usuario.getLogin());
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
            return u;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Usuario> listar() throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            ResultSet r = conexao.prepareStatement("select * from " + Usuario.TABELA_USUARIO).executeQuery();
            List<Usuario> list = new ArrayList<Usuario>();

            while (r.next()) {
                Usuario u = new Usuario();
                u.setAdministrador(r.getBoolean(Usuario.CAMPO_ADMINISTRADOR));
                u.setCadastraProduto(r.getBoolean(Usuario.CAMPO_CADASTRAPRODUTO));
                u.setVendeProduto(r.getBoolean(Usuario.CAMPO_VENDEPRODUTO));
                u.setComissao(r.getDouble(Usuario.CAMPO_COMISSAO));
                u.setLogin(r.getString(Usuario.CAMPO_LOGIN));
                u.setNome(r.getString(Usuario.CAMPO_NOME));
                u.setSenha(r.getString(Usuario.CAMPO_SENHA));
                list.add(u);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Usuario> listar(Usuario usuarioInicial, Usuario usuarioFinal) throws Exception {
        if (usuarioInicial != null) {
            Connection conexao = null;
            try {
                conexao = ConnectionMySql.getConnection();

                StringBuilder str = new StringBuilder();
                str.append("SELECT ");
                str.append(Usuario.CAMPO_LOGIN + ", ");
                str.append(Usuario.CAMPO_SENHA + ", ");
                str.append(Usuario.CAMPO_NOME + ", ");
                str.append(Usuario.CAMPO_COMISSAO + ", ");
                str.append(Usuario.CAMPO_ADMINISTRADOR + ", ");
                str.append(Usuario.CAMPO_CADASTRAPRODUTO + ", ");
                str.append(Usuario.CAMPO_VENDEPRODUTO + " ");
                str.append(" FROM " + Usuario.TABELA_USUARIO);
                str.append(" WHERE ");
                str.append(Usuario.CAMPO_LOGIN + " LIKE ? AND ");
                str.append(Usuario.CAMPO_SENHA + " LIKE ? AND ");
                str.append(Usuario.CAMPO_NOME + " LIKE ? AND ");
                str.append(Usuario.CAMPO_COMISSAO + " LIKE ? ");
                if (usuarioInicial.isAdministrador())
                    str.append(" AND " + Usuario.CAMPO_ADMINISTRADOR + " = ?");
                if (usuarioInicial.isCadastraProduto())
                    str.append(" AND " + Usuario.CAMPO_CADASTRAPRODUTO + " = ?");
                if (usuarioInicial.isVendeProduto())
                    str.append(" AND " + Usuario.CAMPO_VENDEPRODUTO + " = ?");

                PreparedStatement pr = conexao.prepareStatement(str.toString());

                if (usuarioInicial.getLogin() != null) {
                    pr.setString(1, "%" + usuarioInicial.getLogin() + "%");
                } else {
                    pr.setString(1, "%%");
                }

                if (usuarioInicial.getSenha() != null) {
                    pr.setString(2, "%" + usuarioInicial.getSenha() + "%");
                } else {
                    pr.setString(2, "%%");
                }

                if (usuarioInicial.getNome() != null) {
                    pr.setString(3, "%" + usuarioInicial.getNome() + "%");
                } else {
                    pr.setString(3, "%%");
                }

                if (usuarioInicial.getComissao() < 0) {
                    pr.setString(4, "%" + usuarioInicial.getComissao() + "%");
                } else {
                    pr.setString(4, "%%");
                }

                int indice = 5;
                if (usuarioInicial.isAdministrador()) {
                    pr.setBoolean(indice, true);
                    indice++;
                }

                if (usuarioInicial.isCadastraProduto()) {
                    pr.setBoolean(indice, true);
                    indice++;
                }

                if (usuarioInicial.isVendeProduto()) {
                    pr.setBoolean(indice, true);
                }

                ResultSet r = pr.executeQuery();

                List<Usuario> list = new ArrayList<Usuario>();
                while (r.next()) {
                    Usuario u = new Usuario();
                    u.setAdministrador(r.getBoolean(Usuario.CAMPO_ADMINISTRADOR));
                    u.setCadastraProduto(r.getBoolean(Usuario.CAMPO_CADASTRAPRODUTO));
                    u.setVendeProduto(r.getBoolean(Usuario.CAMPO_VENDEPRODUTO));
                    u.setComissao(r.getDouble(Usuario.CAMPO_COMISSAO));
                    u.setLogin(r.getString(Usuario.CAMPO_LOGIN));
                    u.setNome(r.getString(Usuario.CAMPO_NOME));
                    u.setSenha(r.getString(Usuario.CAMPO_SENHA));
                    list.add(u);
                }

                return list;
            } catch (SQLException ex) {
                throw ex;
            } finally {
                ConnectionMySql.closeConnection(conexao);
            }

        } else
            return this.listar();
    }

    @Override
    public int incrementar() throws Exception {
        return 0;
    }
}
