/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.relatorio;

import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Maicon
 */
public class GeradorRelatorio {
    private Relatorio relatorio;
   
    public void setRelatorio(Relatorio relatorio){
        this.relatorio = relatorio;
    }
    
    public void gerarRelatorio(ComposicaoRelatorio composicaoRelatorio) throws JRException{
        relatorio.gerarRelatorio(composicaoRelatorio);
    }
}
