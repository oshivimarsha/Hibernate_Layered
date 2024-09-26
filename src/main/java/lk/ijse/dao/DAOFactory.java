package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMERDAO, ITEMDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){
            case CUSTOMERDAO:
                return  new CustomerDAOImpl();
            case ITEMDAO:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }
}
