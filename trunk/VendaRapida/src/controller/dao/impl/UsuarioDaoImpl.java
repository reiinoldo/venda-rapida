package controller.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import controller.dao.Dao;
import controller.dao.util.ConnectionMongoDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDaoImpl implements Dao<Usuario> {

    private static final String TABELA_USUARIO = "usuario";

    @Override
    public boolean salvar(Usuario usuario) throws SQLException, Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA_USUARIO);

            collection.insert(usuario.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean editar(Usuario usuario) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA_USUARIO);

            collection.update(new BasicDBObject().append("login", usuario.getLogin()), usuario.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Usuario usuario) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA_USUARIO);

            collection.remove(new BasicDBObject("login", usuario.getLogin()));

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public Usuario buscar(Usuario usuario) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA_USUARIO);

            DBCursor cursor = collection.find(new BasicDBObject("login", usuario.getLogin()));
            if (cursor.hasNext()) {
                Usuario usu = new Usuario();
                usu.convertDBObjectToObject(cursor.next());
                return usu;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Usuario> listar() throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA_USUARIO);

            List<Usuario> list = new ArrayList<Usuario>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                Usuario u = new Usuario();
                u.convertDBObjectToObject(cursor.next());
                list.add(u);
            }
            cursor.close();
            return list;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Usuario> listar(Usuario usuarioInicial, Usuario usuarioFinal) throws Exception {
        if (usuarioInicial != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA_USUARIO);

                BasicDBObject filtro = new BasicDBObject();
                if (usuarioInicial.getLogin() != null) {
                    filtro.put("login", java.util.regex.Pattern.compile(usuarioInicial.getLogin()));
                }

                if (usuarioInicial.getSenha() != null) {
                    filtro.put("senha", java.util.regex.Pattern.compile(usuarioInicial.getSenha()));
                }

                if (usuarioInicial.getNome() != null) {
                    filtro.put("nome", java.util.regex.Pattern.compile(usuarioInicial.getNome()));
                }

                if (usuarioInicial.getComissao() < 0) {
                    filtro.put("comissao", java.util.regex.Pattern.compile(usuarioInicial.getComissao() + ""));
                }
                if (usuarioInicial.isAdministrador()) {
                    filtro.put("administrador", true);
                }

                if (usuarioInicial.isCadastraProduto()) {
                    filtro.put("cadastraProduto", true);
                }

                if (usuarioInicial.isVendeProduto()) {
                    filtro.put("vendeProduto", true);
                }
                DBCursor cursor = collection.find(filtro);

                List<Usuario> list = new ArrayList<Usuario>();
                while (cursor.hasNext()) {
                    Usuario u = new Usuario();
                    u.convertDBObjectToObject(cursor.next());
                    list.add(u);
                }
                cursor.close();
                return list;
            } catch (Exception ex) {
                throw ex;
            } finally {
                ConnectionMongoDB.closeConnection(conexao);
            }

        } else {
            return this.listar();
        }
    }

    @Override
    public int incrementar() throws Exception {
        return 0;
    }
}
