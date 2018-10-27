package dao;

import entites.UserPackageEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserPackageEntityDaoImpl implements UserPackageEntityDao{
    /**
     * 根据用户姓名查找所有优惠套餐
     * @param userName
     * @return ArrayList<UserPackageEntity>
     */
    @Override
    public ArrayList<UserPackageEntity> getPackageByUsername(String userName){
        //ArrayList<UserPackageEntity> list = new ArrayList<UserPackageEntity>();
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List list = session.createQuery("from UserPackageEntity ").list();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    public static void main(String[] args) {
        UserPackageEntityDaoImpl u = new UserPackageEntityDaoImpl();
        u.getPackageByUsername("Albert");
    }
}
