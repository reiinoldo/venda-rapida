package controller.dao.impl;

import controller.dao.ItemDao;
import controller.dao.util.ConnectionMySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean salvar(Item item) throws Exception {
        ConnectionMySql.getConnection();

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

        PreparedStatement p = ConnectionMySql.connection.prepareStatement(str.toString());
        p.setInt(1, item.getCodigoVenda());
        p.setString(2, item.getReferenciaProduto());
        p.setInt(3, item.getQuantidade());
        p.setDouble(4, item.getValor());

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return !execution;
    }

    @Override
    public boolean excluir(int codigoVenda, String referenciaProduto) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("delete from " + Item.TABELA_ITEM
                + " where " + Item.CAMPO_CODIGOVENDA + " = ?"
                + " and " + Item.CAMPO_REFERENCIAPRODUTO + " = ?");
        p.setInt(1, codigoVenda);
        p.setString(2, referenciaProduto);

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return execution;
    }

    @Override
    public List<Item> listar(int codigoVenda) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("select * from " + Item.TABELA_ITEM + "where " + Item.CAMPO_CODIGOVENDA + " = ?");
        p.setInt(1, codigoVenda);
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
        ConnectionMySql.closeConnection();
        return list;
    }

    @Override
    public Item buscarItemPorProduto(String referenciaProduto) throws Exception {
        ConnectionMySql.getConnection();
        PreparedStatement ps = ConnectionMySql.connection.prepareStatement("select * from " + Item.TABELA_ITEM + " where " + Item.CAMPO_REFERENCIAPRODUTO + " = ?");
        ps.setString(1, referenciaProduto);
        ResultSet r = ps.executeQuery();

        Item item = null;
        if (r.next()) {
            item = new Item();
            item.setCodigoVenda(r.getInt(Item.CAMPO_CODIGOVENDA));
            item.setQuantidade(r.getInt(Item.CAMPO_QUANTIDADE));
            item.setReferenciaProduto(r.getString(Item.CAMPO_REFERENCIAPRODUTO));
            item.setValor(r.getDouble(Item.CAMPO_VALOR));
        }
        ConnectionMySql.closeConnection();
        return item;
    }
}
