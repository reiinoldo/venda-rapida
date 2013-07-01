package controller;

import java.util.Date;
import java.util.List;
import model.Venda;
import net.sf.jasperreports.engine.JRException;

public interface VendaController {

    Venda buscar(int codigoVenda) throws Exception;

    List<Venda> listar() throws Exception;

    List<Venda> listar(Venda venda, Date dataFinal, Double valorInicial, Double valorFinal) throws Exception;

    void salvar(Venda venda) throws Exception;

    int incrementar() throws Exception;
    
    public void gerarRelatorio(List listaGerada, String path, boolean comItens) throws JRException;
}
