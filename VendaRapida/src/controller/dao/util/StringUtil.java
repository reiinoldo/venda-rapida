package controller.dao.util;

/**
 *
 * @author reinoldo
 */
public class StringUtil {

    public static String returnDigito(String valor) {
        String aux = "";
        for (int i = 0; i < valor.length(); i++) {
            if (Character.isDigit(valor.charAt(i))) {
                aux = aux + valor.charAt(i);
            }
        }
        return aux;
    }
}
