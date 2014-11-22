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
import model.Produto;

public class ProdutoDaoImpl implements Dao<Produto>{

    private static final String TABELA = "produto";

    @Override
    public boolean salvar(Produto produto) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.insert(produto.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Produto produto) throws Exception {
        
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.remove(new BasicDBObject("referencia", produto.getReferencia()));

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<Produto> listar() throws Exception {        
        DB conexao = null;
        
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<Produto> list = new ArrayList<Produto>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                Produto p = new Produto();
                p.convertDBObjectToObject(cursor.next());
                list.add(p);
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
    public List<Produto> listar(Produto produtoInicial, Produto produtoFinal) throws Exception {   
        if (produtoInicial != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                BasicDBObject filtro = new BasicDBObject();                
                if (produtoInicial.getReferencia() != null) {
                    filtro.put("referencia", java.util.regex.Pattern.compile(produtoInicial.getReferencia() + ""));
                }

                if (produtoInicial.getCodigoBarrra() != null) {
                    filtro.put("codigoBarrra", java.util.regex.Pattern.compile(produtoInicial.getCodigoBarrra()));
                }

                if (produtoInicial.getDescricao()!= null) {
                    filtro.put("descricao", java.util.regex.Pattern.compile(produtoInicial.getDescricao()));
                }
                
                if (produtoInicial.getValor() != 0) {
                    filtro.put("valor", new BasicDBObject("$gte", produtoInicial.getValor()));
                }
                
                if (produtoFinal.getValor() != 0) {
                    filtro.put("valor", new BasicDBObject("$lte", produtoFinal.getValor()));
                }                
                
                DBCursor cursor = collection.find(filtro);
                
                List<Produto> list = new ArrayList<Produto>();
                while (cursor.hasNext()) {
                    Produto p = new Produto();
                    p.convertDBObjectToObject(cursor.next());
                    list.add(p);
                }
                cursor.close();
                return list;
            } catch (SQLException ex) {
                throw ex;
            } finally {
                ConnectionMongoDB.closeConnection(conexao);
        }

        } else
            return this.listar();    
        
    }

    @Override
    public boolean editar(Produto produto) throws Exception {            

        if (produto != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                collection.update(new BasicDBObject().append("referencia", produto.getReferencia()), produto.getBasicDBObject());                                        

                return true;
            } catch (SQLException ex) {
                throw ex;
            } finally {
                ConnectionMongoDB.closeConnection(conexao);
            }
        } else {
            throw new SQLException("Produto inválido");
        }
    }

    @Override
    public Produto buscar(Produto produto) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            DBCursor cursor = collection.find(new BasicDBObject("referencia", produto.getReferencia()));
            if (cursor.hasNext()) {
                Produto p = new Produto();
                p.convertDBObjectToObject(cursor.next());
                return p;
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
        return 0;
    }

}
