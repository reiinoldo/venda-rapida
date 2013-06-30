package controller.dao.impl;

import controller.dao.FornecedorDao;
import controller.dao.util.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class FornecedorDaoImpl implements FornecedorDao{

    @Override
    public boolean salvar(Fornecedor fornecedor) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();
            StringBuilder str = new StringBuilder();

            str.append("INSERT INTO " + Fornecedor.TABELA_FORNECEDOR + " ");
            str.append("( " + Fornecedor.CAMPO_ID + ", ");
            str.append(Fornecedor.CAMPO_CPFCNPJ + ", ");
            str.append(Fornecedor.CAMPO_EMAIL + ", ");
            str.append(Fornecedor.CAMPO_ENDERECO + ", ");
            str.append(Fornecedor.CAMPO_NOME + ", ");
            str.append(Fornecedor.CAMPO_TELEFONE + " ) ");
            str.append("values ( ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("?, ");
            str.append("? )");

            PreparedStatement p = conexao.prepareStatement(str.toString());
            p.setInt(1, fornecedor.getId());
            p.setString(2, fornecedor.getCpfCnpj());
            p.setString(3, fornecedor.getEmail());
            p.setString(4, fornecedor.getEndereco());
            p.setString(5, fornecedor.getNome());
            p.setString(6, fornecedor.getTelefone());

            boolean execution = p.execute();

            return !execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public boolean excluir(int id) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("delete from " + Fornecedor.TABELA_FORNECEDOR + " where " + Fornecedor.CAMPO_ID + " = ?");
            p.setInt(1, id);

            boolean execution = p.execute();

            return execution;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Fornecedor> listar() throws Exception {
        Connection conexao = null;
        try{
            conexao = ConnectionMySql.getConnection();

            ResultSet r = conexao.prepareStatement("select * from " + Fornecedor.TABELA_FORNECEDOR).executeQuery();
            List<Fornecedor> list = new ArrayList<Fornecedor>();

            while (r.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(r.getInt(Fornecedor.CAMPO_ID));
                f.setCpfCnpj(r.getString(Fornecedor.CAMPO_CPFCNPJ));
                f.setEmail(r.getString(Fornecedor.CAMPO_EMAIL));
                f.setEndereco(r.getString(Fornecedor.CAMPO_ENDERECO));
                f.setNome(r.getString(Fornecedor.CAMPO_NOME));
                f.setTelefone(r.getString(Fornecedor.CAMPO_TELEFONE));
                list.add(f);
            }
            return list;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }

    @Override
    public List<Fornecedor> listar(Fornecedor fornecedor) throws Exception {
        if (fornecedor != null) {
            Connection conexao = null;
            try {
                conexao = ConnectionMySql.getConnection();

                StringBuilder str = new StringBuilder();
                str.append("SELECT ");
                str.append(Fornecedor.CAMPO_ID + ", ");
                str.append(Fornecedor.CAMPO_CPFCNPJ + ", ");
                str.append(Fornecedor.CAMPO_EMAIL + ", ");
                str.append(Fornecedor.CAMPO_ENDERECO + ", ");
                str.append(Fornecedor.CAMPO_NOME + ", ");
                str.append(Fornecedor.CAMPO_TELEFONE + " ");
                str.append(" FROM " + Fornecedor.TABELA_FORNECEDOR);
                str.append(" WHERE ");
                str.append(Fornecedor.CAMPO_ID + " LIKE ? AND ");
                str.append(Fornecedor.CAMPO_CPFCNPJ + " LIKE ? AND ");
                str.append(Fornecedor.CAMPO_EMAIL + " LIKE ? AND ");
                str.append(Fornecedor.CAMPO_ENDERECO + " LIKE ? AND ");
                str.append(Fornecedor.CAMPO_NOME + " LIKE ? AND ");
                str.append(Fornecedor.CAMPO_TELEFONE + " LIKE ? ");

                PreparedStatement pr = conexao.prepareStatement(str.toString());

                if (fornecedor.getId() != 0) {
                    pr.setString(1, "%" + fornecedor.getId() + "%");
                } else {
                    pr.setString(1, "%%");
                }

                if (fornecedor.getCpfCnpj() != null) {
                    pr.setString(2, "%" + fornecedor.getCpfCnpj() + "%");
                } else {
                    pr.setString(2, "%%");
                }            

                if (fornecedor.getEmail() != null) {
                    pr.setString(3, "%" + fornecedor.getEmail() + "%");
                } else {
                    pr.setString(3, "%%");
                }

                if (fornecedor.getEndereco() != null) {
                    pr.setString(4, "%" + fornecedor.getEndereco() + "%");
                } else {
                    pr.setString(4, "%%");
                }

                if (fornecedor.getNome() != null) {
                    pr.setString(5, "%" + fornecedor.getNome() + "%");
                } else {
                    pr.setString(5, "%%");
                }

                if (fornecedor.getTelefone() != null) {
                    pr.setString(6, "%" + fornecedor.getTelefone() + "%");
                } else {
                    pr.setString(6, "%%");
                }

                ResultSet r = pr.executeQuery();

                List<Fornecedor> list = new ArrayList<Fornecedor>();
                while (r.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setId(r.getInt(Fornecedor.CAMPO_ID));
                    f.setCpfCnpj(r.getString(Fornecedor.CAMPO_CPFCNPJ));
                    f.setEmail(r.getString(Fornecedor.CAMPO_EMAIL));
                    f.setEndereco(r.getString(Fornecedor.CAMPO_ENDERECO));
                    f.setNome(r.getString(Fornecedor.CAMPO_NOME));
                    f.setTelefone(r.getString(Fornecedor.CAMPO_TELEFONE));
                    list.add(f);
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
    public boolean editar(Fornecedor fornecedor) throws Exception {
        if (fornecedor != null) {
            Connection conexao = null;
            try {
                conexao = ConnectionMySql.getConnection();

                StringBuilder sb = new StringBuilder();
                sb.append("update "+ Fornecedor.TABELA_FORNECEDOR +" ");
                sb.append("set ");
                sb.append(Fornecedor.CAMPO_CPFCNPJ +  " = ?, ");
                sb.append(Fornecedor.CAMPO_EMAIL +  " = ?, ");
                sb.append(Fornecedor.CAMPO_ENDERECO +  " = ?, ");
                sb.append(Fornecedor.CAMPO_NOME +  " = ?, ");
                sb.append(Fornecedor.CAMPO_TELEFONE +  " = ? ");
                sb.append("where ");
                sb.append(Fornecedor.CAMPO_ID +  " = ?");

                PreparedStatement pr = conexao.prepareStatement(sb.toString());

                pr.setString(1, fornecedor.getCpfCnpj());
                pr.setString(2, fornecedor.getEmail());
                pr.setString(3, fornecedor.getEndereco());
                pr.setString(4, fornecedor.getNome());
                pr.setString(5, fornecedor.getTelefone());
                pr.setInt(6, fornecedor.getId());

                boolean execution = pr.execute();
                return execution;
            } catch (SQLException ex) {
                throw ex;
            } finally {
                ConnectionMySql.closeConnection(conexao);
            }
        } else {
            throw new SQLException("Fornecedor inválido");
        }
    }

    @Override
    public Fornecedor buscar(int id) throws Exception {
        Connection conexao = null;
        try {
            conexao = ConnectionMySql.getConnection();

            PreparedStatement p = conexao.prepareStatement("select * from " + Fornecedor.TABELA_FORNECEDOR + " where " + Fornecedor.CAMPO_ID + " = ?");
            p.setInt(1, id);
            ResultSet r = p.executeQuery();

            Fornecedor f = null; 
            if (r.next()) {
                f = new Fornecedor();
                f.setId(r.getInt(Fornecedor.CAMPO_ID));
                f.setCpfCnpj(r.getString(Fornecedor.CAMPO_CPFCNPJ));
                f.setEmail(r.getString(Fornecedor.CAMPO_EMAIL));
                f.setEndereco(r.getString(Fornecedor.CAMPO_ENDERECO));
                f.setNome(r.getString(Fornecedor.CAMPO_NOME));
                f.setTelefone(r.getString(Fornecedor.CAMPO_TELEFONE));
            }
            return f;
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
            int r = ConnectionMySql.nextId(Fornecedor.TABELA_FORNECEDOR, Fornecedor.CAMPO_ID, conexao);
            return r;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionMySql.closeConnection(conexao);
        }
    }
}
