package controller.dao.impl;

import controller.dao.ClienteDao;
import controller.dao.util.ConnectionMySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDaoImpl implements ClienteDao {

    @Override
    public boolean salvar(Cliente cliente) throws Exception {
        ConnectionMySql.getConnection();

        StringBuilder str = new StringBuilder();

        str.append("INSERT INTO " + Cliente.TABELA_CLIENTE + " ");
        str.append("( " + Cliente.CAMPO_ID + ", ");
        str.append(Cliente.CAMPO_CPFCNPJ + ", ");
        str.append(Cliente.CAMPO_EMAIL + ", ");
        str.append(Cliente.CAMPO_ENDERECO + ", ");
        str.append(Cliente.CAMPO_NOME + ", ");
        str.append(Cliente.CAMPO_TELEFONE + " ) ");
        str.append("values ( ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("?, ");
        str.append("? )");

        PreparedStatement p = ConnectionMySql.connection.prepareStatement(str.toString());
        p.setInt(1, cliente.getId());
        p.setString(2, cliente.getCpfCnpj());
        p.setString(3, cliente.getEmail());
        p.setString(4, cliente.getEndereco());
        p.setString(5, cliente.getNome());
        p.setString(6, cliente.getTelefone());

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return !execution;
    }

    @Override
    public boolean excluir(int id) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("delete from " + Cliente.TABELA_CLIENTE + " where " + Cliente.CAMPO_ID + " = ?");
        p.setInt(1, id);

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return execution;
    }

    @Override
    public List<Cliente> listar() throws Exception {
        ConnectionMySql.getConnection();

        ResultSet r = ConnectionMySql.connection.prepareStatement("select * from " + Cliente.TABELA_CLIENTE).executeQuery();
        List<Cliente> list = new ArrayList<Cliente>();

        while (r.next()) {
            Cliente f = new Cliente();
            f.setId(r.getInt(Cliente.CAMPO_ID));
            f.setCpfCnpj(r.getString(Cliente.CAMPO_CPFCNPJ));
            f.setEmail(r.getString(Cliente.CAMPO_EMAIL));
            f.setEndereco(r.getString(Cliente.CAMPO_ENDERECO));
            f.setNome(r.getString(Cliente.CAMPO_NOME));
            f.setTelefone(r.getString(Cliente.CAMPO_TELEFONE));
            list.add(f);
        }
        ConnectionMySql.closeConnection();
        return list;
    }

    @Override
    public List<Cliente> listar(Cliente cliente) throws Exception {
        if (cliente != null) {
            StringBuilder str = new StringBuilder();
            str.append("SELECT ");
            str.append(Cliente.CAMPO_ID + ", ");
            str.append(Cliente.CAMPO_CPFCNPJ + ", ");
            str.append(Cliente.CAMPO_EMAIL + ", ");
            str.append(Cliente.CAMPO_ENDERECO + ", ");
            str.append(Cliente.CAMPO_NOME + ", ");
            str.append(Cliente.CAMPO_TELEFONE + " ");
            str.append(" FROM " + Cliente.TABELA_CLIENTE);
            str.append(" WHERE ");
            str.append(Cliente.CAMPO_ID + " LIKE ? AND ");
            str.append(Cliente.CAMPO_CPFCNPJ + " LIKE ? AND ");
            str.append(Cliente.CAMPO_EMAIL + " LIKE ? AND ");
            str.append(Cliente.CAMPO_ENDERECO + " LIKE ? AND ");
            str.append(Cliente.CAMPO_NOME + " LIKE ? AND ");
            str.append(Cliente.CAMPO_TELEFONE + " LIKE ?");

            ConnectionMySql.getConnection();

            PreparedStatement pr = ConnectionMySql.connection.prepareStatement(str.toString());

            if (cliente.getId() != 0) {
                pr.setString(1, "%" + cliente.getId() + "%");
            } else {
                pr.setString(1, "%%");
            }

            if (cliente.getCpfCnpj() != null) {
                pr.setString(2, "%" + cliente.getCpfCnpj() + "%");
            } else {
                pr.setString(2, "%%");
            }

            if (cliente.getEmail() != null) {
                pr.setString(3, "%" + cliente.getEmail() + "%");
            } else {
                pr.setString(3, "%%");
            }

            if (cliente.getEndereco() != null) {
                pr.setString(4, "%" + cliente.getEndereco() + "%");
            } else {
                pr.setString(4, "%%");
            }

            if (cliente.getNome() != null) {
                pr.setString(5, "%" + cliente.getNome() + "%");
            } else {
                pr.setString(5, "%%");
            }

            if (cliente.getTelefone() != null) {
                pr.setString(6, "%" + cliente.getTelefone() + "%");
            } else {
                pr.setString(6, "%%");
            }

            ResultSet r = pr.executeQuery();

            List<Cliente> list = new ArrayList<Cliente>();
            while (r.next()) {
                Cliente c = new Cliente();
                c.setId(r.getInt(Cliente.CAMPO_ID));
                c.setCpfCnpj(r.getString(Cliente.CAMPO_CPFCNPJ));
                c.setEmail(r.getString(Cliente.CAMPO_EMAIL));
                c.setEndereco(r.getString(Cliente.CAMPO_ENDERECO));
                c.setNome(r.getString(Cliente.CAMPO_NOME));
                c.setTelefone(r.getString(Cliente.CAMPO_TELEFONE));
                list.add(c);
            }

            ConnectionMySql.closeConnection();
            return list;

        } else {
            return this.listar();
        }
    }

    @Override
    public boolean editar(Cliente cliente) throws Exception {
        if (cliente != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("update " + Cliente.TABELA_CLIENTE + " ");
            sb.append("set ");
            sb.append(Cliente.CAMPO_CPFCNPJ + " = ?, ");
            sb.append(Cliente.CAMPO_EMAIL + " = ?, ");
            sb.append(Cliente.CAMPO_ENDERECO + " = ?, ");
            sb.append(Cliente.CAMPO_NOME + " = ?, ");
            sb.append(Cliente.CAMPO_TELEFONE + " = ? ");
            sb.append("where ");
            sb.append(Cliente.CAMPO_ID + " = ?");

            ConnectionMySql.getConnection();
            PreparedStatement pr = ConnectionMySql.connection.prepareStatement(sb.toString());

            pr.setString(1, cliente.getCpfCnpj());
            pr.setString(2, cliente.getEmail());
            pr.setString(3, cliente.getEndereco());
            pr.setString(4, cliente.getNome());
            pr.setString(5, cliente.getTelefone());
            pr.setInt(6, cliente.getId());

            boolean execution = pr.execute();
            ConnectionMySql.closeConnection();
            return execution;
        } else {
            throw new SQLException("Cliente inválido");
        }
    }

    @Override
    public Cliente buscar(int id) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("select * from " + Cliente.TABELA_CLIENTE + " where " + Cliente.CAMPO_ID + " = ?");
        p.setInt(1, id);
        ResultSet r = p.executeQuery();

        Cliente c = null;
        if (r.next()) {
            c = new Cliente();
            c.setId(r.getInt(Cliente.CAMPO_ID));
            c.setCpfCnpj(r.getString(Cliente.CAMPO_CPFCNPJ));
            c.setEmail(r.getString(Cliente.CAMPO_EMAIL));
            c.setEndereco(r.getString(Cliente.CAMPO_ENDERECO));
            c.setNome(r.getString(Cliente.CAMPO_NOME));
            c.setTelefone(r.getString(Cliente.CAMPO_TELEFONE));
        }
        ConnectionMySql.closeConnection();
        return c;
    }

    @Override
    public int incrementar() throws Exception {
        ConnectionMySql.getConnection();
        int r = ConnectionMySql.nextId(Cliente.TABELA_CLIENTE, Cliente.CAMPO_ID);
        ConnectionMySql.closeConnection();
        return r;
    }
}
