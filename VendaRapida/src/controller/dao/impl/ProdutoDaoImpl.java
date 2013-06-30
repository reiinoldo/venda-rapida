package controller.dao.impl;

import controller.dao.ProdutoDao;
import controller.dao.util.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDaoImpl implements ProdutoDao{

    @Override
    public boolean salvar(Produto produto) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO " + Produto.TABELA_PRODUTO + " ");
            str.append("( " + Produto.CAMPO_REFERENCIA + ", ");
            str.append(Produto.CAMPO_CODIGOBARRA + ", ");
            str.append(Produto.CAMPO_DESCRICAO + ", ");
            str.append(Produto.CAMPO_VALOR + " ) ");
            str.append("values ( ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("? )");

            PreparedStatement p = conexao.prepareStatement(str.toString());
            p.setString(1, produto.getReferencia());
            p.setString(2, produto.getCodigoBarrra());
            p.setString(3, produto.getDescricao());
            p.setDouble(4, produto.getValor());

            boolean execution = p.execute();

            return !execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(String referencia) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("delete from " + Produto.TABELA_PRODUTO + " where " + Produto.CAMPO_REFERENCIA + " = ?");
            p.setString(1, referencia);

            boolean execution = p.execute();

            return execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Produto> listar() throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            ResultSet r = conexao.prepareStatement("select * from " + Produto.TABELA_PRODUTO).executeQuery();
            List<Produto> list = new ArrayList<Produto>();

            while (r.next()) {
                Produto p = new Produto();
                p.setReferencia(r.getString(Produto.CAMPO_REFERENCIA));
                p.setCodigoBarrra(r.getString(Produto.CAMPO_CODIGOBARRA));
                p.setDescricao(r.getString(Produto.CAMPO_DESCRICAO));
                p.setValor(r.getDouble(Produto.CAMPO_VALOR));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Produto> listar(Produto produto, double valorFinal) throws Exception {   
        if (produto != null) {
            Connection conexao = null;
            try {
                conexao = ConnectionMySql.getConnection();

                StringBuilder str = new StringBuilder();
                str.append("SELECT ");
                str.append(Produto.CAMPO_REFERENCIA + ", ");
                str.append(Produto.CAMPO_CODIGOBARRA + ", ");
                str.append(Produto.CAMPO_DESCRICAO + ", ");
                str.append(Produto.CAMPO_VALOR + " ");
                str.append(" FROM " + Produto.TABELA_PRODUTO);
                str.append(" WHERE ");
                str.append(Produto.CAMPO_REFERENCIA + " LIKE ? AND ");
                str.append(Produto.CAMPO_CODIGOBARRA + " LIKE ? AND ");
                str.append(Produto.CAMPO_DESCRICAO + " LIKE ? AND ");
                str.append(Produto.CAMPO_VALOR + " >= ? AND ");
                str.append(Produto.CAMPO_VALOR + " <= ?");

                PreparedStatement pr = conexao.prepareStatement(str.toString());

                if (produto.getReferencia() != null) {
                    pr.setString(1, "%" + produto.getReferencia() + "%");
                } else {
                    pr.setString(1, "%%");
                }

                if (produto.getCodigoBarrra() != null) {
                    pr.setString(2, "%" + produto.getCodigoBarrra() + "%");
                } else {
                    pr.setString(2, "%%");
                }            

                if (produto.getDescricao() != null) {
                    pr.setString(3, "%" + produto.getDescricao() + "%");
                } else {
                    pr.setString(3, "%%");
                }

                if (produto.getValor() != 0) {
                    pr.setDouble(4, produto.getValor() );
                } else {
                    pr.setDouble(4, 0);
                }

                if (valorFinal != 0) {
                    pr.setDouble(5, valorFinal );
                } else {
                    pr.setDouble(5, Double.MAX_VALUE);
                }

                ResultSet r = pr.executeQuery();

                List<Produto> list = new ArrayList<Produto>();
                while (r.next()) {
                    Produto p = new Produto();

                    p.setReferencia(r.getString(Produto.CAMPO_REFERENCIA));
                    p.setCodigoBarrra(r.getString(Produto.CAMPO_CODIGOBARRA));
                    p.setDescricao(r.getString(Produto.CAMPO_DESCRICAO));
                    p.setValor(r.getDouble(Produto.CAMPO_VALOR));
                    list.add(p);
                }

                return list;
            } catch (SQLException ex) {
                throw ex;
            } finally {
                ConnectionMySql.closeConnection(conexao);
        }

        } else
            return this.listar();
    }

        @Override
        public boolean editar(Produto produto) throws Exception {
            if (produto != null) {
                Connection conexao = null;
                try {
                    conexao = ConnectionMySql.getConnection();
                    StringBuilder sb = new StringBuilder();
                    sb.append("update "+ Produto.TABELA_PRODUTO +" ");
                    sb.append("set ");
                    sb.append(Produto.CAMPO_CODIGOBARRA +  " = ?, ");
                    sb.append(Produto.CAMPO_DESCRICAO +  " = ?, ");
                    sb.append(Produto.CAMPO_VALOR +  " = ? ");
                    sb.append("where ");
                    sb.append(Produto.CAMPO_REFERENCIA +  " = ?");

                    PreparedStatement pr = conexao.prepareStatement(sb.toString());

                    pr.setString(1, produto.getCodigoBarrra());
                    pr.setString(2, produto.getDescricao());
                    pr.setDouble(3, produto.getValor());
                    pr.setString(4, produto.getReferencia());

                    boolean execution = pr.execute();

                    return execution;
                } catch (SQLException ex) {
                    throw ex;
                } finally {
                    ConnectionMySql.closeConnection(conexao);
                }
            } else {
                throw new SQLException("Produto inválido");
            }
        }

    @Override
    public Produto buscar(String referencia) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement ps = conexao.prepareStatement("select * from " + Produto.TABELA_PRODUTO + " where " + Produto.CAMPO_REFERENCIA + " = ?");
            ps.setString(1, referencia);
            ResultSet r = ps.executeQuery();

            Produto p = null; 
            if (r.next()) {
                p = new Produto();
                p.setReferencia(r.getString(Produto.CAMPO_REFERENCIA));
                p.setCodigoBarrra(r.getString(Produto.CAMPO_CODIGOBARRA));
                p.setDescricao(r.getString(Produto.CAMPO_DESCRICAO));
                p.setValor(r.getDouble(Produto.CAMPO_VALOR));
            }
            return p;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public Produto buscarCodigoBarras(String codigoBarras) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement ps = conexao.prepareStatement("select * from " + Produto.TABELA_PRODUTO + " where " + Produto.CAMPO_CODIGOBARRA + " = ?");
            ps.setString(1, codigoBarras);
            ResultSet r = ps.executeQuery();

            Produto p = null; 
            if (r.next()) {
                p = new Produto();
                p.setReferencia(r.getString(Produto.CAMPO_REFERENCIA));
                p.setCodigoBarrra(r.getString(Produto.CAMPO_CODIGOBARRA));
                p.setDescricao(r.getString(Produto.CAMPO_DESCRICAO));
                p.setValor(r.getDouble(Produto.CAMPO_VALOR));
            }
            return p;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

}
