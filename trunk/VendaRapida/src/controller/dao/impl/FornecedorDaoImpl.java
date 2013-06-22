package controller.dao.impl;

import controller.dao.FornecedorDao;
import controller.dao.util.ConnectionMySql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class FornecedorDaoImpl implements FornecedorDao{

    @Override
    public boolean salvar(Fornecedor fornecedor) throws Exception {
        ConnectionMySql.getConnection();

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

        PreparedStatement p = ConnectionMySql.connection.prepareStatement(str.toString());
        p.setInt(1, fornecedor.getId());
        p.setString(2, fornecedor.getCpfCnpj());
        p.setString(3, fornecedor.getEmail());
        p.setString(4, fornecedor.getEndereco());
        p.setString(5, fornecedor.getNome());
        p.setString(6, fornecedor.getTelefone());

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return !execution;
    }

    @Override
    public boolean excluir(int id) throws Exception {
        ConnectionMySql.getConnection();

        PreparedStatement p = ConnectionMySql.connection.prepareStatement("delete from " + Fornecedor.TABELA_FORNECEDOR + " where " + Fornecedor.CAMPO_ID + " = ?");
        p.setInt(1, id);

        boolean execution = p.execute();
        ConnectionMySql.closeConnection();

        return execution;
    }

    @Override
    public List<Fornecedor> listar() throws Exception {
        ConnectionMySql.getConnection();
        
        ResultSet r = ConnectionMySql.connection.prepareStatement("select * from " + Fornecedor.TABELA_FORNECEDOR).executeQuery();
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
        ConnectionMySql.closeConnection();
        return list;
    }

    @Override
    public List<Fornecedor> listar(Fornecedor fornecedor) throws Exception {
        if (fornecedor != null) {
            StringBuilder str = new StringBuilder();
            str.append("SELECT ");
            str.append(Fornecedor.CAMPO_ID + ", ");
            str.append(Fornecedor.CAMPO_CPFCNPJ + ", ");
            str.append(Fornecedor.CAMPO_EMAIL + ", ");
            str.append(Fornecedor.CAMPO_ENDERECO + ", ");
            str.append(Fornecedor.CAMPO_NOME + ", ");
            str.append(Fornecedor.CAMPO_TELEFONE + ", ");
            str.append(" FROM " + Fornecedor.TABELA_FORNECEDOR);
            str.append(" WHERE ");
            str.append(Fornecedor.CAMPO_ID + " LIKE ? AND ");
            str.append(Fornecedor.CAMPO_CPFCNPJ + "LIKE ? AND ");
            str.append(Fornecedor.CAMPO_EMAIL + " LIKE ? AND ");
            str.append(Fornecedor.CAMPO_ENDERECO + " LIKE ? AND ");
            str.append(Fornecedor.CAMPO_NOME + "LIKE ? AND ");
            str.append(Fornecedor.CAMPO_TELEFONE + " LIKE ? AND ");

            ConnectionMySql.getConnection();

            PreparedStatement pr = ConnectionMySql.connection.prepareStatement(str.toString());

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
            
            ConnectionMySql.closeConnection();
            return list;

        } else
            return this.listar();
    }

    @Override
    public boolean editar(Fornecedor fornecedor) throws Exception {
            if (fornecedor != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("update "+ Fornecedor.TABELA_FORNECEDOR +" ");
                sb.append("set ");
                sb.append(Fornecedor.CAMPO_CPFCNPJ +  " = ?, ");
                sb.append(Fornecedor.CAMPO_EMAIL +  " = ?, ");
                sb.append(Fornecedor.CAMPO_ENDERECO +  " = ? ");
                sb.append(Fornecedor.CAMPO_NOME +  " = ?, ");
                sb.append(Fornecedor.CAMPO_TELEFONE +  " = ? ");
                sb.append("where ");
                sb.append(Fornecedor.CAMPO_ID +  " = ?");
        
                ConnectionMySql.getConnection();
                PreparedStatement pr = ConnectionMySql.connection.prepareStatement(sb.toString());

                pr.setString(1, fornecedor.getCpfCnpj());
                pr.setString(2, fornecedor.getEmail());
                pr.setString(3, fornecedor.getEndereco());
                pr.setString(4, fornecedor.getNome());
                pr.setString(5, fornecedor.getTelefone());
                pr.setInt(6, fornecedor.getId());
                
                boolean execution = pr.execute();
                ConnectionMySql.closeConnection();
                return execution;
            } else {
                throw new SQLException("Fornecedor inválido");
            }
    }

    @Override
    public Fornecedor buscar(int id) throws Exception {
        ConnectionMySql.getConnection();
        
        PreparedStatement p = ConnectionMySql.connection.prepareStatement("select * from " + Fornecedor.TABELA_FORNECEDOR + "where " + Fornecedor.CAMPO_ID + " = ?");
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
        ConnectionMySql.closeConnection();
        return f;
    }    

    @Override
    public int incrementar() throws Exception {
        return ConnectionMySql.nextId(Fornecedor.TABELA_FORNECEDOR, Fornecedor.CAMPO_ID);
    }
}
