/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.relatorio.formato;

import controller.relatorio.ComposicaoRelatorio;
import controller.relatorio.Relatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Maicon
 */
public class RelatorioPDF implements Relatorio {

    private String nomeRelatorio;

    @Override
    public void gerarRelatorio(ComposicaoRelatorio composicaoRelatorio) throws JRException {
        JasperReport report = JasperCompileManager.compileReport(composicaoRelatorio.getTipoRelatorio().getCaminhoRelatorio());
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(composicaoRelatorio.getLista()));
        this.nomeRelatorio = composicaoRelatorio.getLocalSalvar() + ".pdf";
        JasperExportManager.exportReportToPdfFile(print, this.nomeRelatorio);
    }

    @Override
    public String getNomeRelatorio() {
        return this.nomeRelatorio;
    }
}
