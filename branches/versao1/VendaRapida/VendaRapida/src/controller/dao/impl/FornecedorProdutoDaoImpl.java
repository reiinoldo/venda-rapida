package controller.dao.impl;

import controller.dao.Dao;
import controller.dao.util.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FornecedorProduto;

public class FornecedorProdutoDaoImpl implements Dao<FornecedorProduto>{

    @Override
    public boolean salvar(FornecedorProduto fornecedorProduto) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + " ");
            str.append("( " + FornecedorProduto.CAMPO_IDFORNECEDOR + ", ");
            str.append(FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " ) ");
            str.append("values ( ");
            str.append("?, ");
            str.append("? )");

            PreparedStatement p = conexao.prepareStatement(str.toString());
            p.setInt(1, fornecedorProduto.getIdFornecdor());
            p.setString(2, fornecedorProduto.getReferenciaProduto());

            boolean execution = p.execute();

            return !execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(FornecedorProduto fornecedorProduto) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("delete from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + 
                                                                              " where " + FornecedorProduto.CAMPO_IDFORNECEDOR + " = ?" + 
                                                                              " and " + FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " = ?");
            p.setInt(1, fornecedorProduto.getIdFornecdor());
            p.setString(2, fornecedorProduto.getReferenciaProduto());

            boolean execution = p.execute();

            return execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<FornecedorProduto> listar(FornecedorProduto fornecedorProdutoInicial, FornecedorProduto fornecedorProdutoFinal) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            List<FornecedorProduto> list = new ArrayList<FornecedorProduto>();
            
            if (fornecedorProdutoInicial.getReferenciaProduto() == null) {
                PreparedStatement p = conexao.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + " where " + FornecedorProduto.CAMPO_IDFORNECEDOR + " = ?");
                p.setInt(1, fornecedorProdutoInicial.getIdFornecdor());
                ResultSet r = p.executeQuery();

                while (r.next()) {
                    FornecedorProduto fp = new FornecedorProduto();
                    fp.setIdFornecdor(r.getInt(FornecedorProduto.CAMPO_IDFORNECEDOR));
                    fp.setReferenciaProduto(r.getString(FornecedorProduto.CAMPO_REFERENCIAPRODUTO));
                    list.add(fp);
                }
            } else {
                PreparedStatement p = conexao.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + " where " + FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " = ?");
                p.setString(1, fornecedorProdutoInicial.getReferenciaProduto());
                ResultSet r = p.executeQuery();

                while (r.next()) {
                    FornecedorProduto fp = new FornecedorProduto();
                    fp.setIdFornecdor(r.getInt(FornecedorProduto.CAMPO_IDFORNECEDOR));
                    fp.setReferenciaProduto(r.getString(FornecedorProduto.CAMPO_REFERENCIAPRODUTO));
                    list.add(fp);
                }
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public FornecedorProduto buscar(FornecedorProduto fornecedorProduto) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();
        
            PreparedStatement p = conexao.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO + 
                                                                              " where " + FornecedorProduto.CAMPO_IDFORNECEDOR + " = ?" + 
                                                                              " and " + FornecedorProduto.CAMPO_REFERENCIAPRODUTO + " = ?");
            p.setInt(1, fornecedorProduto.getIdFornecdor());
            p.setString(2, fornecedorProduto.getReferenciaProduto());
            ResultSet r = p.executeQuery();

            FornecedorProduto fp = null; 
            if (r.next()) {
                fp = new FornecedorProduto();
                fp.setIdFornecdor(r.getInt(FornecedorProduto.CAMPO_IDFORNECEDOR));
                fp.setReferenciaProduto(r.getString(FornecedorProduto.CAMPO_REFERENCIAPRODUTO));
            }
            return fp;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<FornecedorProduto> listar() throws Exception {
        Connection conexao = null;
        try {
        conexao = ConnectionMySql.getConnection();

        List<FornecedorProduto> list = new ArrayList<FornecedorProduto>();
        PreparedStatement p = conexao.prepareStatement("select * from " + FornecedorProduto.TABELA_FORNECEDORPRODUTO);
        ResultSet r = p.executeQuery();

            while (r.next()) {
                FornecedorProduto fp = new FornecedorProduto();
                fp.setIdFornecdor(r.getInt(FornecedorProduto.CAMPO_IDFORNECEDOR));
                fp.setReferenciaProduto(r.getString(FornecedorProduto.CAMPO_REFERENCIAPRODUTO));
                list.add(fp);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
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
