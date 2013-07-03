/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.impl;

import controller.dao.VendaDao;
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
import model.Venda;

/**
 *
 * @author andrebampi
 */
public class VendaDaoImpl implements VendaDao {

    @Override
    public boolean salvar(Venda venda) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO " + Venda.TABELA_VENDA + " ");
            str.append("( " + Venda.CAMPO_CODIGOVENDA + ", ");
            str.append(Venda.CAMPO_CODIGOPAGSEGURO + ", ");
            str.append(Venda.CAMPO_DATAVENDA + ", ");
            str.append(Venda.CAMPO_DESCONTO + ", ");
            str.append(Venda.CAMPO_IDCLIENTE + ", ");
            str.append(Venda.CAMPO_LOGINUSUARIO + " ) ");
            str.append("values ( ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("? )");

            PreparedStatement p = conexao.prepareStatement(str.toString());
            p.setInt(1, venda.getCodigoVenda());
            p.setString(2, venda.getCodigoPagSeguro());
            p.setTimestamp(3, new Timestamp(venda.getDataVenda().getTime()));
            p.setDouble(4, venda.getDesconto());
            p.setInt(5, venda.getIdCliente());
            p.setString(6, venda.getLoginUsuario());

            boolean execution = p.execute();


            return !execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Venda> listar() throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            ResultSet r = conexao.prepareStatement("select * from " + Venda.TABELA_VENDA).executeQuery();
            List<Venda> list = new ArrayList<Venda>();

            while (r.next()) {
                Venda v = new Venda();
                v.setCodigoVenda(r.getInt(Venda.CAMPO_CODIGOVENDA));
                v.setCodigoPagSeguro(r.getString(Venda.CAMPO_CODIGOPAGSEGURO));
                v.setDataVenda(r.getTimestamp(Venda.CAMPO_DATAVENDA));
                v.setDesconto(r.getDouble(Venda.CAMPO_DESCONTO));
                v.setIdCliente(r.getInt(Venda.CAMPO_IDCLIENTE));
                v.setLoginUsuario(r.getString(Venda.CAMPO_LOGINUSUARIO));
                v.setItems(null);
                list.add(v);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Venda> listar(Venda venda, Date dataFinal) throws Exception {
        if (venda != null) {
            Connection conexao = null;
            try {
                conexao = ConnectionMySql.getConnection();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                StringBuilder str = new StringBuilder();
                str.append("SELECT ");
                str.append(Venda.CAMPO_CODIGOVENDA + ", ");
                str.append(Venda.CAMPO_CODIGOPAGSEGURO + ", ");
                str.append(Venda.CAMPO_DATAVENDA + ", ");
                str.append(Venda.CAMPO_DESCONTO + ", ");
                str.append(Venda.CAMPO_IDCLIENTE + ", ");
                str.append(Venda.CAMPO_LOGINUSUARIO + " ");
                str.append(" FROM " + Venda.TABELA_VENDA);
                str.append(" WHERE ");
                str.append(Venda.CAMPO_CODIGOVENDA + " LIKE ? AND ");
                str.append(Venda.CAMPO_CODIGOPAGSEGURO + " LIKE ? AND ");
                str.append(Venda.CAMPO_DATAVENDA + " >= ? AND ");
                str.append(Venda.CAMPO_DATAVENDA + " <= ? AND ");
                str.append(Venda.CAMPO_DESCONTO + " LIKE ? AND ");
                str.append(Venda.CAMPO_IDCLIENTE + " LIKE ? AND ");
                str.append(Venda.CAMPO_LOGINUSUARIO + " LIKE ?");

                PreparedStatement pr = conexao.prepareStatement(str.toString());

                if (venda.getCodigoVenda() != 0) {
                    pr.setString(1, "%" + venda.getCodigoVenda() + "%");
                } else {
                    pr.setString(1, "%%");
                }

                if (venda.getCodigoPagSeguro() != null) {
                    pr.setString(2, "%" + venda.getCodigoPagSeguro() + "%");
                } else {
                    pr.setString(2, "%%");
                }

                if (venda.getDataVenda() != null) {
                    pr.setTimestamp(3, new Timestamp(venda.getDataVenda().getTime()));
                } else {
                    try {
                        pr.setTimestamp(3, new Timestamp(format.parse("01/01/1910").getTime()));
                    } catch (ParseException ex) {
                        Logger.getLogger(VendaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (dataFinal != null) {
                    pr.setTimestamp(4, new Timestamp(dataFinal.getTime()));
                } else {
                    pr.setTimestamp(4, new Timestamp(new Date().getTime()));
                }

                if (venda.getDesconto() > 0) {
                    pr.setString(5, "%" + venda.getDesconto() + "%");
                } else {
                    pr.setString(5, "%%");
                }

                if (venda.getIdCliente() != 0) {
                    pr.setString(6, venda.getIdCliente() + "");
                } else {
                    pr.setString(6, "%%");
                }

                if (venda.getLoginUsuario() != null) {
                    pr.setString(7, "%" + venda.getLoginUsuario() + "%");
                } else {
                    pr.setString(7, "%%");
                }

                ResultSet r = pr.executeQuery();

                List<Venda> list = new ArrayList<Venda>();
                while (r.next()) {
                    Venda v = new Venda();
                    v.setCodigoVenda(r.getInt(Venda.CAMPO_CODIGOVENDA));
                    v.setCodigoPagSeguro(r.getString(Venda.CAMPO_CODIGOPAGSEGURO));
                    v.setDataVenda(r.getTimestamp(Venda.CAMPO_DATAVENDA));
                    v.setDesconto(r.getDouble(Venda.CAMPO_DESCONTO));
                    v.setIdCliente(r.getInt(Venda.CAMPO_IDCLIENTE));
                    v.setLoginUsuario(r.getString(Venda.CAMPO_LOGINUSUARIO));
                    list.add(v);
                }

                return list;
            } catch (SQLException ex) {
                throw ex;
            } finally {
                ConnectionMySql.closeConnection(conexao);
            }

        } else {
            return this.listar();
        }
    }

    @Override
    public Venda buscar(int codigoVenda) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("select * from " + Venda.TABELA_VENDA + " where " + Venda.CAMPO_CODIGOVENDA + " = ?");
            p.setInt(1, codigoVenda);
            ResultSet r = p.executeQuery();

            Venda v = null;
            if (r.next()) {
                v = new Venda();
                v.setCodigoVenda(r.getInt(Venda.CAMPO_CODIGOVENDA));
                v.setCodigoPagSeguro(r.getString(Venda.CAMPO_CODIGOPAGSEGURO));
                v.setDataVenda(r.getTimestamp(Venda.CAMPO_DATAVENDA));
                v.setDesconto(r.getDouble(Venda.CAMPO_DESCONTO));
                v.setIdCliente(r.getInt(Venda.CAMPO_IDCLIENTE));
                v.setLoginUsuario(r.getString(Venda.CAMPO_LOGINUSUARIO));
                v.setItems(null);
            }
            return v;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public int incrementar() throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();
            int r = ConnectionMySql.nextId(Venda.TABELA_VENDA, Venda.CAMPO_CODIGOVENDA, conexao);
            return r;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }
}
