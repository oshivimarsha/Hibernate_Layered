package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("select * from Customer");
        query.addEntity(Customer.class);
        ArrayList<Customer> customers = (ArrayList<Customer>) query.list();


        transaction.commit();
        session.close();

        return customers;

    }

    @Override
    public boolean save(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(customer);
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

        NativeQuery query3 = session.createNativeQuery("delete from Customer where id =?1");
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

        Object query3 = session.createNativeQuery("SELECT id FROM Customer ORDER BY CAST(SUBSTRING(id, 2) AS UNSIGNED) DESC LIMIT 1").uniqueResult();

        if (query3 != null) {
            String id = query3.toString();
            String[] split = id.split("C");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "C" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "C" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "C" + ++idNum;
            }
        }
        transaction.commit();
        session.close();
        return "C001";
    }

}
