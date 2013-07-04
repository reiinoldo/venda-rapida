/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.relatorio;

import java.util.List;

/**
 *
 * @author Maicon
 */
public class ComposicaoRelatorio {

    private List lista;
    private TipoRelatorio tipoRelatorio;
    private String localSalvar;

    public ComposicaoRelatorio(List lista, TipoRelatorio tipoRelatorio) {
        this.lista = lista;
        this.tipoRelatorio = tipoRelatorio;
    }

    public List getLista() {
        return lista;
    }

    public TipoRelatorio getTipoRelatorio() {
        return tipoRelatorio;
    }

    public String getLocalSalvar() {
        return localSalvar + tipoRelatorio.getNome();
    }

    public void setLocalSalvar(String localSalvar) {
        this.localSalvar = localSalvar;
    }
}
