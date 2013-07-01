package controller.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMySql {

    private static String serverName = "localhost";//"localhost";    //caminho do servidor do BD
    private static String mydatabase = "vendarapida";
    private static String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
    private static String username = "root";        //nome de um usuário de seu BD      
    private static String password = "123";      //sua senha de acesso

    private ConnectionMySql() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            String driverName = "org.gjt.mm.mysql.Driver";
            Class.forName(driverName);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static int nextId(String tabela, String primaryKeyName, Connection connection) throws SQLException {
        ResultSet r = connection.prepareStatement("select max(" + primaryKeyName + ") from " + tabela).executeQuery();

        if (r.next()) {
            int id = r.getInt(1) + 1;
            r.close();
            return id;
        }
        return 1;
    }
}
