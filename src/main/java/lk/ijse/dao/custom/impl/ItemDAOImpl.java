package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("select * from Item");
        query.addEntity(Item.class);
        ArrayList<Item> items = (ArrayList<Item>) query.list();


        transaction.commit();
        session.close();

        return items;

    }

    @Override
    public boolean save(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(item);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(item);
            transaction.commit();
            session.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean delete(String nic) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query3 = session.createNativeQuery("delete from Item where id =?1");
        query3.setParameter(1,1);
        query3.executeUpdate();

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNewID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object query3 = session.createNativeQuery("SELECT id FROM Item ORDER BY CAST(SUBSTRING(id, 2) AS UNSIGNED) DESC LIMIT 1").uniqueResult();

        if (query3 != null) {
            String id = query3.toString();
            String[] split = id.split("I");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "I" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "I" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "I" + ++idNum;
            }
        }
        transaction.commit();
        session.close();
        return "I001";
    }
}
