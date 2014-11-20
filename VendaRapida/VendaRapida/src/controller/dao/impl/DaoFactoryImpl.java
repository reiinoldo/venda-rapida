package controller.dao.impl;

import controller.dao.Dao;
import controller.dao.DaoFactory;
import controller.dao.TipoDao;

/**
 *
 * @author andrebampi
 */
public class DaoFactoryImpl implements DaoFactory {

    @Override
    public Dao CriarDao(TipoDao tipoDao) {
        switch(tipoDao) {
            case CLIENTE           : return new ClienteDaoImpl();
            case FORNECEDOR        : return new FornecedorDaoImpl();
            case FORNECEDORPRODUTO : return new FornecedorProdutoDaoImpl();
            case ITEM              : return new ItemDaoImpl();
            case PRODUTO           : return new ProdutoDaoImpl();
            case USUARIO           : return new UsuarioDaoImpl();
            case VENDA             : return new VendaDaoImpl();
            default: return null;
        }
    }
    
}
