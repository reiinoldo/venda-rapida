package controller.dao.util;

import java.text.DecimalFormat;

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

    public static Double getValorR$(String valor) {
        valor = valor.replace(",", ".");

        return Double.parseDouble(valor);
    }

    public static String getR$FormmatedFromDouble(Double valor) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(valor);
    }
}
