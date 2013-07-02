/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.FrmConsultaPreco;

/**
 *
 * @author andrebampi
 */
public class ThreadLimpaComponentesFrmConsultaPreco extends Thread{

    private FrmConsultaPreco frmConsultaPreco;
    
    public ThreadLimpaComponentesFrmConsultaPreco(FrmConsultaPreco frmConsultaPreco) {
        this.frmConsultaPreco = frmConsultaPreco;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            frmConsultaPreco.edCodigoBarras.setText("");
            frmConsultaPreco.edPrecoTotal.setText("0,00");
            frmConsultaPreco.edQuantidade.setText("1");
            frmConsultaPreco.lbDescricao.setText("Descrição do produto");
        } catch (InterruptedException ex) {
            interrupt();
        }
    }
    
}
