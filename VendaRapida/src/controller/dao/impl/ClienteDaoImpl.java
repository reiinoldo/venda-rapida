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
import model.Cliente;

public class ClienteDaoImpl implements Dao<Cliente> {

    private static final String TABELA = "cliente";

    @Override
    public boolean salvar(Cliente cliente) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.insert(cliente.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Cliente cliente) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.remove(new BasicDBObject("id", cliente.getId()));

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Cliente> listar() throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<Cliente> list = new ArrayList<Cliente>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                Cliente c = new Cliente();
                c.convertDBObjectToObject(cursor.next());
                list.add(c);
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
    public List<Cliente> listar(Cliente clienteInicial, Cliente clienteFinal) throws Exception {
        if (clienteInicial != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                BasicDBObject filtro = new BasicDBObject();
                if (clienteInicial.getId() < 0) {
                    filtro.put("id", java.util.regex.Pattern.compile(clienteInicial.getId() + ""));
                }

                if (clienteInicial.getCpfCnpj() != null) {
                    filtro.put("cpfCnpj", java.util.regex.Pattern.compile(clienteInicial.getCpfCnpj()));
                }

                if (clienteInicial.getEmail() != null) {
                    filtro.put("email", java.util.regex.Pattern.compile(clienteInicial.getEmail()));
                }

                if (clienteInicial.getEndereco() != null) {
                    filtro.put("endereco", java.util.regex.Pattern.compile(clienteInicial.getEndereco()));
                }

                if (clienteInicial.getNome() != null) {
                    filtro.put("nome", java.util.regex.Pattern.compile(clienteInicial.getNome()));
                }

                if (clienteInicial.getTelefone() != null) {
                    filtro.put("telefone", java.util.regex.Pattern.compile(clienteInicial.getTelefone()));
                }

                DBCursor cursor = collection.find(filtro);

                List<Cliente> list = new ArrayList<Cliente>();
                while (cursor.hasNext()) {
                    Cliente c = new Cliente();
                    c.convertDBObjectToObject(cursor.next());
                    list.add(c);
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
    public boolean editar(Cliente cliente) throws Exception {
        if (cliente != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                collection.update(new BasicDBObject().append("id", cliente.getId()), cliente.getBasicDBObject());

                return true;
            } catch (Exception ex) {
                throw ex;
            } finally {
                ConnectionMongoDB.closeConnection(conexao);
            }
        } else {
            throw new SQLException("Cliente inválido");
        }
    }

    @Override
    public Cliente buscar(Cliente cliente) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            DBCursor cursor = collection.find(new BasicDBObject("id", cliente.getId()));
            if (cursor.hasNext()) {
                Cliente cli = new Cliente();
                cli.convertDBObjectToObject(cursor.next());
                return cli;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public int incrementar() throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            return ConnectionMongoDB.nextId(collection, "id");
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }
}
