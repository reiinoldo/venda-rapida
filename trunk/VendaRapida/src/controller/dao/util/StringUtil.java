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
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException ex) {
            throw ex;
        }
    }

    public static String getR$FormmatedFromDouble(Double valor) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(valor);
    }
}
