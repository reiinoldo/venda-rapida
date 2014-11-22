package controller.dao.impl;

import com.mongodb.DB;
import controller.dao.Dao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import controller.dao.util.ConnectionMongoDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class FornecedorDaoImpl implements Dao<Fornecedor>{

    private static final String TABELA = "fornecedor";
    
    @Override
    public boolean salvar(Fornecedor fornecedor) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);
            
            collection.insert(fornecedor.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Fornecedor fornecedor) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.remove(new BasicDBObject("id", fornecedor.getId()));

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Fornecedor> listar() throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<Fornecedor> list = new ArrayList<Fornecedor>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                Fornecedor f = new Fornecedor();
                f.convertDBObjectToObject(cursor.next());
                list.add(f);
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
    public List<Fornecedor> listar(Fornecedor fornecedorInicial, Fornecedor fornecedorFinal) throws Exception {
        if (fornecedorInicial != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                BasicDBObject filtro = new BasicDBObject();
                if (fornecedorInicial.getId() < 0) {
                    filtro.put("id", java.util.regex.Pattern.compile(fornecedorInicial.getId() + ""));
                }

                if (fornecedorInicial.getCpfCnpj() != null) {
                    filtro.put("cpfCnpj", java.util.regex.Pattern.compile(fornecedorInicial.getCpfCnpj()));
                }

                if (fornecedorInicial.getEmail() != null) {
                    filtro.put("email", java.util.regex.Pattern.compile(fornecedorInicial.getEmail()));
                }

                if (fornecedorInicial.getEndereco() != null) {
                    filtro.put("endereco", java.util.regex.Pattern.compile(fornecedorInicial.getEndereco()));
                }

                if (fornecedorInicial.getNome() != null) {
                    filtro.put("nome", java.util.regex.Pattern.compile(fornecedorInicial.getNome()));
                }

                if (fornecedorInicial.getTelefone() != null) {
                    filtro.put("telefone", java.util.regex.Pattern.compile(fornecedorInicial.getTelefone()));
                }

                DBCursor cursor = collection.find(filtro);

                List<Fornecedor> list = new ArrayList<Fornecedor>();
                while (cursor.hasNext()) {
                    Fornecedor f = new Fornecedor();
                    f.convertDBObjectToObject(cursor.next());
                    list.add(f);
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
    public boolean editar(Fornecedor fornecedor) throws Exception {
        if (fornecedor != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                collection.update(new BasicDBObject().append("id", fornecedor.getId()), fornecedor.getBasicDBObject());

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
    public Fornecedor buscar(Fornecedor fornecedor) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            DBCursor cursor = collection.find(new BasicDBObject("id", fornecedor.getId()));
            if (cursor.hasNext()) {
                Fornecedor f = new Fornecedor();
                f.convertDBObjectToObject(cursor.next());
                return f;
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
