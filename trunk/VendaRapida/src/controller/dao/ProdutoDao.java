package controller.dao;

import java.util.List;
import model.Produto;

public interface ProdutoDao {
    public boolean salvar(Produto produto) throws Exception;
    public boolean excluir(String referencia) throws Exception;
    public Produto buscar(String referencia) throws Exception;
    public List<Produto> listar() throws Exception;
    public List<Produto> listar(Produto produto, double valorFinal) throws Exception;
    public boolean editar(Produto produto) throws Exception;
}