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
            frmConsultaPreco.limpar();
        } catch (InterruptedException ex) {
            interrupt();
        }
    }
    
}
