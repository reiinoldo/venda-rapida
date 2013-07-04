package controller.dao;

/**
 *
 * @author andrebampi
 */
public interface DaoFactory<T> {
    Dao<T> CriarDao();
}
