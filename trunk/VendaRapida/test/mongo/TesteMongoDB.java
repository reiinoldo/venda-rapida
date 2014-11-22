/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;

/**
 *
 * @author Milena
 */
public class TesteMongoDB {

    public static void main(String[] args) throws UnknownHostException {
        Mongo m = new Mongo("localhost", 27017);
        DB db = m.getDB("venda_rapida");
        DBCollection usuarioCollection = db.getCollection("usuario");

        Usuariox usuario = new Usuariox();
        usuario.setAdministrador(true);
        usuario.setCadastraProduto(true);
        usuario.setComissao(0);
        usuario.setLogin("admin");
        usuario.setNome("Administrador");
        usuario.setSenha("admin");
        usuario.setVendeProduto(true);

//        usuarioCollection.insert(usuario.getBasicDBObject());



        DBCursor cursor = usuarioCollection.find();
        DBObject doc;
        while (cursor.hasNext()) {
            doc = cursor.next();
            Usuariox usuariox = new Usuariox();
            usuariox.convertDBObjectToObject(doc);

            System.out.println(usuario.getLogin());
            System.out.println(usuario.getComissao());
            System.out.println(usuario.getNome());
            System.out.println(usuario.getSenha());
        }
        cursor.close();

    }
}
