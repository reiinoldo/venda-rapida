/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 *
 * @author Maicon
 */
public class ConnectionMongoDB {

    private static final String HOST = "localhost";
    private static final String DB_NAME = "venda_rapida";
    private static final int PORT = 27017;

    public static DB getConnection() throws UnknownHostException {
        Mongo m = new Mongo(HOST, PORT);
        return m.getDB(DB_NAME);
    }

    public static void closeConnection(DB connection) {
        if (connection != null) {
            connection.getMongo().close();
        }
    }

    public static int nextId(DBCollection collection, String idFieldName) throws SQLException {
        DBObject sort = new BasicDBObject();
        sort.put(idFieldName, -1);

        DBCursor cursor = collection.find().sort(sort).limit(1);
        try {
            if (cursor.hasNext()) {
                Integer valor = (Integer) cursor.next().get(idFieldName);
                return valor+1;
            }
            return 1;
        } finally {
            cursor.close();
        }
    }
}
