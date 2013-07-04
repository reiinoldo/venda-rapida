package controller.dao.impl;

import controller.dao.Dao;
import controller.dao.util.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemDaoImpl implements Dao<Item> {

    @Override
    public boolean salvar(Item item) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO " + Item.TABELA_ITEM + " ");
            str.append("( " + Item.CAMPO_CODIGOVENDA + ", ");
            str.append(Item.CAMPO_REFERENCIAPRODUTO + ", ");
            str.append(Item.CAMPO_QUANTIDADE + ", ");
            str.append(Item.CAMPO_VALOR + " ) ");
            str.append("values ( ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("? )");

            PreparedStatement p = conexao.prepareStatement(str.toString());
            p.setInt(1, item.getCodigoVenda());
            p.setString(2, item.getReferenciaProduto());
            p.setInt(3, item.getQuantidade());
            p.setDouble(4, item.getValor());

            boolean execution = p.execute();

            return !execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(Item item) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("delete from " + Item.TABELA_ITEM
                    + " where " + Item.CAMPO_CODIGOVENDA + " = ?"
                    + " and " + Item.CAMPO_REFERENCIAPRODUTO + " = ?");
            p.setInt(1, item.getCodigoVenda());
            p.setString(2, item.getReferenciaProduto());

            boolean execution = p.execute();

            return execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Item> listar(Item itemInicial, Item itemFinal) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("select * from " + Item.TABELA_ITEM + " where " + Item.CAMPO_CODIGOVENDA + " = ?");
            p.setInt(1, itemInicial.getCodigoVenda());
            ResultSet r = p.executeQuery();

            List<Item> list = new ArrayList<Item>();

            while (r.next()) {
                Item i = new Item();
                i.setCodigoVenda(r.getInt(Item.CAMPO_CODIGOVENDA));
                i.setReferenciaProduto(r.getString(Item.CAMPO_REFERENCIAPRODUTO));
                i.setQuantidade(r.getInt(Item.CAMPO_QUANTIDADE));
                i.setValor(r.getDouble(Item.CAMPO_VALOR));
                list.add(i);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public Item buscar(Item item) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();
            PreparedStatement ps = conexao.prepareStatement("select * from " + Item.TABELA_ITEM + " where " + Item.CAMPO_REFERENCIAPRODUTO + " = ?");
            ps.setString(1, item.getReferenciaProduto());
            ResultSet r = ps.executeQuery();

            Item i = null;
            if (r.next()) {
                i = new Item();
                i.setCodigoVenda(r.getInt(Item.CAMPO_CODIGOVENDA));
                i.setQuantidade(r.getInt(Item.CAMPO_QUANTIDADE));
                i.setReferenciaProduto(r.getString(Item.CAMPO_REFERENCIAPRODUTO));
                i.setValor(r.getDouble(Item.CAMPO_VALOR));
            }
            return i;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Item> listar() throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            ResultSet r = conexao.prepareStatement("select * from " + Item.TABELA_ITEM).executeQuery();
            List<Item> list = new ArrayList<Item>();

            while (r.next()) {
                Item i = new Item();
                i.setCodigoVenda(r.getInt(Item.CAMPO_CODIGOVENDA));
                i.setReferenciaProduto(r.getString(Item.CAMPO_REFERENCIAPRODUTO));
                i.setQuantidade(r.getInt(Item.CAMPO_QUANTIDADE));
                i.setValor(r.getInt(Item.CAMPO_VALOR));
                list.add(i);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
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
