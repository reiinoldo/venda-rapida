package controller.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionMySql {

    private static String serverName = "MAICONM";//"localhost";    //caminho do servidor do BD
    private static String mydatabase = "vendarapida";
    private static String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
    private static String username = "root";        //nome de um usuário de seu BD      
    private static String password = "1234";      //sua senha de acesso
    
    public static Connection connection;
    
    private ConnectionMySql(){
    }

    public static void getConnection() throws SQLException {
        if (connection == null) {
            try {
                String driverName = "org.gjt.mm.mysql.Driver";
                Class.forName(driverName);

                connection = DriverManager.getConnection(url, username, password);
                return;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new SQLException("Conexão já instanciada.");
    }
    
    public static void closeConnection() throws SQLException{
        connection.close();
        connection = null;
    }
    
    public static int nextId(String tabela, String primaryKeyName) throws SQLException{        
        ResultSet r = connection.prepareStatement("select max("+primaryKeyName+") from "+tabela).executeQuery();
        
        if(r.next()){
            int id = r.getInt(1)+1;
            r.close();
            return id;
        } 
        return 1;
    }
        
}
