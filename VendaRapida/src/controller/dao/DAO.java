package controller.dao;

import java.util.List;

/**
 *
 * @author andrebampi
 */
public interface DAO<T> {
    public boolean salvar(T info) throws Exception;
    public boolean excluir(T identificador) throws Exception;
    public T buscar(T identificador) throws Exception;
    public List<T> listar() throws Exception;
    public List<T> listar(T infoInicial, T infoFinal) throws Exception;
    public boolean editar(T info) throws Exception;
    public int incrementar() throws Exception;
}
