/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalFileChooserUI;

/**
 *
 * @author Maicon
 */
public class ViewUtil {

    public enum GeradorNomePDF {

        PRODUTO("Produto"), VENDAS("Vendas");
        private String nomeRel;
        private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

        GeradorNomePDF(String nomeRel) {
            this.nomeRel = nomeRel;
        }

        public String getNomePdf() {
            return this.nomeRel + " " + sdf.format(new Date()) + ".pdf";
        }
    }

    /**
     * Método que cria um JFileChooser e caso selecionado um path, retorna a
     * string completa com caminho completo do arquivo a salvar.
     *
     * @param view
     * @param namePdf
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static String createFileChooserToSavePDF(Component view, GeradorNomePDF nomePDF) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
        String nomeRelatorio = nomePDF.getNomePdf();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        MetalFileChooserUI ui = (MetalFileChooserUI) fileChooser.getUI();
        Field field;

        field = MetalFileChooserUI.class.getDeclaredField("fileNameTextField");
        field.setAccessible(true);
        JTextField tf = (JTextField) field.get(ui);
        tf.setEditable(false);
        tf.setEnabled(false);

        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(view);

        if (fileChooser.getSelectedFile() != null) {
            if (fileChooser.getSelectedFile().isDirectory()) {
                return fileChooser.getSelectedFile()
                        + "/" + nomeRelatorio;
            }
        }
        return null;
    }
}
