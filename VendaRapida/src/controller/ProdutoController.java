package controller;

import java.util.List;
import model.Produto;
import net.sf.jasperreports.engine.JRException;

public interface ProdutoController {

    Produto buscar(String referencia) throws Exception;

    void editar(Produto produto) throws Exception;

    void excluir(String referencia) throws Exception;

    List<Produto> listar() throws Exception;

    List<Produto> listar(Produto produto, double valorFinal) throws Exception;

    void salvar(Produto produto) throws Exception;

    void gerarRelatorio(List listaGerada, String path) throws JRException;
    
}
