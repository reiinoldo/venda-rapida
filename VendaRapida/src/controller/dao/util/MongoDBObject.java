/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Maicon
 */
public abstract class MongoDBObject {

    public BasicDBObject getBasicDBObject() throws Exception {
        BasicDBObject obj = new BasicDBObject();

        Field[] field = getClass().getDeclaredFields();
        for (Field f : field) {
            if (f.getType().isPrimitive()
                    || f.getType().equals(String.class)
                    || f.getType().equals(Double.class)
                    || f.getType().equals(Float.class)
                    || f.getType().equals(Short.class)
                    || f.getType().equals(Integer.class)
                    || f.getType().equals(Date.class)) {
                String name = f.getName();
                f.setAccessible(true);
                obj.put(name, f.get(this));
            }
        }

        return obj;
    }

    public void convertDBObjectToObject(DBObject object) throws Exception {
        Map objMap = object.toMap();
        for (Object nameField : objMap.keySet()) {
            if (nameField.toString().equals("_id")) {
                continue;
            }
            Field f = getClass().getDeclaredField(nameField.toString());
            f.setAccessible(true);
            f.set(this, objMap.get(nameField));
        }
    }
}
