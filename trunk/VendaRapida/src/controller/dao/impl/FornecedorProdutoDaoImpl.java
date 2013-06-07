package controller.dao.impl;

import controller.dao.FornecedorDao;
import controller.dao.FornecedorProdutoDao;
import controller.dao.ProdutoDao;
import controller.dao.util.ConnectionMySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import model.FornecedorProduto;
import model.Produto;

public class FornecedorProdutoDaoImpl implements FornecedorProdutoDao{

    @Override
    public boolean salvar(FornecedorProduto fornecedorProduto) throws Exception {
        ConnectionMySql.getConnection();

        StringBuilder str = new StringBuilder();

        str.append("INSERT INTO " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + " ");
        str.append("( " + FornecedorProduto.CAMPO_IDFORNECEDOR + ", ");
        str.append(FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " ) ");
        str.append("values ( ");
        str.append("?, ");
        str.append("? )");

        PreparedStatement p = ConnectionMySql.connection.prepareStatement(str.toString());
        p.setInt(1, fornecedorProduto.getIdFornecdor());
        p.setString(2, fornecedorProduto.getReferenciaProduto());

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return !execution;
    }

    @Override
    public boolean excluir(int idFornecedor, String referenciaProduto) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("delete from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + 
                                                                          " where " + FornecedorProduto.CAMPO_IDFORNECEDOR + " = ?" + 
                                                                          " and " + FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " = ?");
        p.setInt(1, idFornecedor);
        p.setString(2, referenciaProduto);

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return execution;
    }

    @Override
    public List<Produto> listarProdutos(int idFornecedor) throws Exception {
        ConnectionMySql.getConnection();
        
        PreparedStatement ps = ConnectionMySql.connection.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + "where " + FornecedorProduto.CAMPO_IDFORNECEDOR + " = ?");
        ps.setInt(1, idFornecedor);
        ResultSet r = ps.executeQuery();
        List<Produto> list = new ArrayList<>();

        while (r.next()) {
            ProdutoDao p = new ProdutoDaoImpl();
            list.add(p.buscar(r.getString(FornecedorProduto.CAMPO_REFERENCIAPRODUTO)));
        }
        ConnectionMySql.closeConnection();
        return list;
    }

    @Override
    public List<Fornecedor> listarFornecedores(String referenciaProduto) throws Exception {
        ConnectionMySql.getConnection();
        
        PreparedStatement p = ConnectionMySql.connection.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + "where " + FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " = ?");
        p.setString(1, referenciaProduto);
        ResultSet r = p.executeQuery();
        List<Fornecedor> list = new ArrayList<>();

        while (r.next()) {
            FornecedorDao f = new FornecedorDaoImpl();
            list.add(f.buscar(r.getInt(FornecedorProduto.CAMPO_IDFORNECEDOR)));
        }
        ConnectionMySql.closeConnection();
        return list;
    }

    @Override
    public FornecedorProduto buscar(int idFornecedor, String referenciaProduto) throws Exception {
        ConnectionMySql.getConnection();
        
        PreparedStatement p = ConnectionMySql.connection.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + 
                                                                          " where " + FornecedorProduto.CAMPO_IDFORNECEDOR + " = ?" + 
                                                                          " and " + FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " = ?");
        p.setInt(1, idFornecedor);
        p.setString(2, referenciaProduto);
        ResultSet r = p.executeQuery();

        FornecedorProduto fp = null; 
        if (r.next()) {
            fp = new FornecedorProduto();
            fp.setIdFornecdor(r.getInt(FornecedorProduto.CAMPO_IDFORNECEDOR));
            fp.setReferenciaProduto(r.getString(FornecedorProduto.CAMPO_REFERENCIAPRODUTO));
        }
        ConnectionMySql.closeConnection();
        return fp;
    }
    
}
