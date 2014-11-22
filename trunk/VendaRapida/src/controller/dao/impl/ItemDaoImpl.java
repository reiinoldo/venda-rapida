package controller.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import controller.dao.Dao;
import controller.dao.util.ConnectionMongoDB;
import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemDaoImpl implements Dao<Item> {

    private static final String TABELA = "item";

    @Override
    public boolean salvar(Item item) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.insert(item.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Item item) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);
            
            BasicDBObject find = new BasicDBObject();
            find.put("codigoVenda", item.getCodigoVenda());
            find.put("referenciaProduto",item.getReferenciaProduto());
            
            collection.remove(find);

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Item> listar(Item itemInicial, Item itemFinal) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);
            
            List<Item> list = new ArrayList<Item>();

            DBCursor cursor = collection.find(new BasicDBObject("codigoVenda", itemInicial.getCodigoVenda()));
            while (cursor.hasNext()) {
                Item i = new Item();
                i.convertDBObjectToObject(cursor.next());
                list.add(i);
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
    public Item buscar(Item item) throws Exception {
        
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            DBCursor cursor = collection.find(new BasicDBObject("referenciaProduto", item.getReferenciaProduto()));
            while (cursor.hasNext()) {
                Item i = new Item();
                i.convertDBObjectToObject(cursor.next());
                cursor.close();
                return i;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Item> listar() throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<Item> list = new ArrayList<Item>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                Item i = new Item();
                i.convertDBObjectToObject(cursor.next());
                list.add(i);
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
    public boolean editar(Item info) throws Exception {
        throw new UnsupportedOperationException("Ítens da venda não são editáveis");
    }

    @Override
    public int incrementar() throws Exception {
        return 0;
    }
}
