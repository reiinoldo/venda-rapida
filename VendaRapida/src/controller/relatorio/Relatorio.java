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
public interface Relatorio {

    public void gerarRelatorio(ComposicaoRelatorio composicaoRelatorio) throws JRException;
    
    public String getNomeRelatorio();
}
