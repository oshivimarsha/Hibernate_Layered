package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMERBO, ITEMBO
    }

    public SuperBO getBO(BOTypes boTypes){

        switch (boTypes){
            case CUSTOMERBO:
                return new CustomerBOImpl();
            case ITEMBO:
                return new ItemBOImpl();
            default:
                return null;
        }
    }
}
