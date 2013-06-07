package controller.dao;

import java.util.Date;
import java.util.List;
import model.Venda;

public interface VendaDao {
     public boolean salvar(Venda venda) throws Exception;
     public Venda buscar(int codigoVenda) throws Exception;
     public List<Venda> listar() throws Exception;
     public List<Venda> listar(Venda venda, Date dataFinal, Double valorInicial, Double valorFinal) throws Exception;
}
