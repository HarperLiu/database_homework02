package utils;

import entites.DataRecordEntity;
import entites.DiscountPackageEntity;
import entites.PhoneRecordEntity;
import entites.UserPackageEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateSessionFactory {
    private static final Logger logger = Logger.getLogger(HibernateSessionFactory.class.getName());
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        logger.setLevel(Level.ALL);
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            //一定要记得在这里把生成的实体类加入到Configuration里。否则调用的时候肯定报错。
            //Hibernate 4.x版本的不需要。5.x才需要这样。而且5.x的hibernate必须要生成.hbm.xml文件，否则还是会报错。4.x只需要把生成的实体类在hibernate.cfg.xml中声明即可。
            configuration.addClass(DataRecordEntity.class)
                    .addClass(DiscountPackageEntity.class)
                    .addClass(PhoneRecordEntity.class)
                    .addClass(UserPackageEntity.class);

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.getCurrentSession();
    }

    public static void main(final String[] args) throws Exception {
        /*
        final Session session = getSession();
        try {
            session.beginTransaction();
            UserEntity entity = session.load(UserEntity.class, 1);
            //只有在使用对象时，它才发出查询SQL语句，加载对象。
            System.out.println("User.Password="+entity.getPassword());
            logger.log(Level.FINEST, entity.getCellphone());
            //因为此的user为persistent状态，所以数据库进行同步
            entity.setIdentityCard("342401199209081211");
            session.getTransaction().commit();

        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
*/
    }
}
