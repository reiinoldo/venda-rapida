package controller.dao;

/**
 *
 * @author andrebampi
 */
public interface DaoFactory {
    Dao CriarDao(TipoDao tipoDao);
}
