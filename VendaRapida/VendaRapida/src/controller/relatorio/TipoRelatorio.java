/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.relatorio;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum TipoRelatorio {

    PRODUTOS("Produtos", "src/relatorios/relProdutos.jrxml"),
    VENDAS("Vendas", "src/relatorios/relVendas.jrxml"),
    VENDAS_COM_ITENS("Vendas", "src/relatorios/relVendasComItens.jrxml"),
    CLIENTES("Clientes", "src/relatorios/relClientes.jrxml"),
    FORNECEDORES("Fornecedores", "src/relatorios/relFornecedores.jrxml");
    private String nomeRelatorio;
    private String caminhoRelatorio;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

    TipoRelatorio(String nomeRelatorio, String caminhoRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
        this.caminhoRelatorio = caminhoRelatorio;
    }

    public String getNome() {
        return this.nomeRelatorio + " " + sdf.format(new Date());
    }

    public String getCaminhoRelatorio() {
        return caminhoRelatorio;
    }
}
