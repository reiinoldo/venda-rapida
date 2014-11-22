package controller.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import controller.dao.Dao;
import controller.dao.util.ConnectionMongoDB;
import java.util.ArrayList;
import java.util.List;
import model.FornecedorProduto;

public class FornecedorProdutoDaoImpl implements Dao<FornecedorProduto> {

    private static final String TABELA = "fornecedor_produto";

    @Override
    public boolean salvar(FornecedorProduto fornecedorProduto) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.insert(fornecedorProduto.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(FornecedorProduto fornecedorProduto) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            collection.remove(fornecedorProduto.getBasicDBObject());

            return true;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<FornecedorProduto> listar(FornecedorProduto fornecedorProdutoInicial, FornecedorProduto fornecedorProdutoFinal) throws Exception {

        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<FornecedorProduto> list = new ArrayList<FornecedorProduto>();

            if (fornecedorProdutoInicial.getReferenciaProduto() == null) {
                DBCursor cursor = collection.find(new BasicDBObject("idFornecedor", fornecedorProdutoInicial.getIdFornecedor()));

                while (cursor.hasNext()) {
                    FornecedorProduto fp = new FornecedorProduto();
                    fp.convertDBObjectToObject(cursor.next());
                    list.add(fp);
                }
                cursor.close();
            } else {
                DBCursor cursor = collection.find(new BasicDBObject("referenciaProduto", fornecedorProdutoInicial.getReferenciaProduto()));

                while (cursor.hasNext()) {
                    FornecedorProduto fp = new FornecedorProduto();
                    fp.convertDBObjectToObject(cursor.next());
                    list.add(fp);
                }
                cursor.close();
            }
            return list;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public FornecedorProduto buscar(FornecedorProduto fornecedorProduto) throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            DBCursor cursor = collection.find(fornecedorProduto.getBasicDBObject());
            while (cursor.hasNext()) {
                FornecedorProduto fp = new FornecedorProduto();
                fp.convertDBObjectToObject(cursor.next());
                cursor.close();
                return fp;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionMongoDB.closeConnection(conexao);
        }
    }

    @Override
    public List<FornecedorProduto> listar() throws Exception {
        DB conexao = null;
        try {
            conexao = ConnectionMongoDB.getConnection();
            DBCollection collection = conexao.getCollection(TABELA);

            List<FornecedorProduto> list = new ArrayList<FornecedorProduto>();

            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                FornecedorProduto fp = new FornecedorProduto();
                fp.convertDBObjectToObject(cursor.next());
                list.add(fp);
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
    public boolean editar(FornecedorProduto info) throws Exception {
        throw new UnsupportedOperationException("Não é possível editar a relação fornecedor/produto. Exclua e insira novamente");
    }

    @Override
    public int incrementar() throws Exception {
        return 0;
    }
}