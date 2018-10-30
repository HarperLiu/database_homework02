package utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class idHelper {
    /**
     * 获取id
     * @param tableName
     * @return
     */
    public static int getTableId(String tableName){
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        List entites = new ArrayList<>();
        try {
            entites = session.createQuery("from "+tableName).list();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return entites.size()+1;
    }
}
