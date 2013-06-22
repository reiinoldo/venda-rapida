package controller.dao;

import java.util.List;
import model.Item;

public interface ItemDao {
    public boolean salvar(Item item) throws Exception;
    public boolean excluir(int codigoVenda, String referenciaProduto) throws Exception;
    public List<Item> listar(int codigoVenda) throws Exception;
    public Item buscarItemPorProduto(String referenciaProduto)throws Exception;
}
