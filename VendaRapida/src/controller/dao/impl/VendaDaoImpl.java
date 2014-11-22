/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import controller.dao.Dao;
import controller.dao.util.ConnectionMongoDB;
import controller.dao.util.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Venda;

public class VendaDaoImpl implements Dao<Venda> {
    
    private static final String TABELA = "venda";

    @Override
    public boolean salvar(Venda venda) throws Exception {
        
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.insert(venda.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }        
    }

    @Override
    public List<Venda> listar() throws Exception {
        
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<Venda> list = new ArrayList<Venda>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                Venda v = new Venda();
                v.convertDBObjectToObject(cursor.next());
                list.add(v);
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
    public List<Venda> listar(Venda vendaInicial, Venda vendaFinal) throws Exception {
        if (vendaInicial != null) {
            DB conexao = null;
            try {
                conexao = ConnectionMongoDB.getConnection();
                DBCollection collection = conexao.getCollection(TABELA);

                BasicDBObject filtro = new BasicDBObject();
                if (vendaInicial.getCodigoVenda() < 0) {
                    filtro.put("codigoVenda", new BasicDBObject("$gte", vendaInicial.getCodigoVenda()).append("$lte", vendaInicial.getCodigoVenda()));
                }

                if (vendaInicial.getCodigoPagSeguro() != null) {
                    filtro.put("codigoPagSeguro", java.util.regex.Pattern.compile(vendaInicial.getCodigoPagSeguro()));
                }                
                
                if (vendaInicial.getValor() != 0) {
                    filtro.put("valor", new BasicDBObject("$gte", vendaInicial.getValor()).append("$lte", vendaFinal.getValor()));
                }
                
                if (vendaInicial.getDataVenda() != null) {
                    filtro.put("dataVenda", new BasicDBObject("$gte", vendaInicial.getDataVenda()).append("$lte", vendaFinal.getDataVenda()));
                }                                
                
                if (vendaInicial.getIdCliente() != 0) {
                    filtro.put("idCliente", vendaInicial.getIdCliente());
                }
                
                if (vendaInicial.getLoginUsuario()!= null) {
                    filtro.put("loginUsuario", java.util.regex.Pattern.compile(vendaInicial.getLoginUsuario()));
                }                
                
                DBCursor cursor = collection.find(filtro);

                List<Venda> list = new ArrayList<Venda>();
                while (cursor.hasNext()) {
                    Venda v = new Venda();
                    v.convertDBObjectToObject(cursor.next());
                    list.add(v);
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
    public Venda buscar(Venda venda) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            DBCursor cursor = collection.find(new BasicDBObject("codigoVenda", venda.getCodigoVenda()));
            if (cursor.hasNext()) {
                Venda v = new Venda();
                v.convertDBObjectToObject(cursor.next());
                return v;
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

            return ConnectionMongoDB.nextId(collection, "codigoVenda");
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
        
    }

    @Override
    public boolean excluir(Venda identificador) throws Exception {
        throw new UnsupportedOperationException("Não é possível excluir uma venda.");
    }

    @Override
    public boolean editar(Venda info) throws Exception {
        throw new UnsupportedOperationException("Não é possível editar uma venda.");
    }
}
