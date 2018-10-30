package dao;

import entites.PhoneRecordEntity;
import entites.UserPackageEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;
import utils.ResultMessage;
import utils.idHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UserPackageEntityDaoImpl implements UserPackageEntityDao{
    /**
     * 根据用户姓名查找所有优惠套餐
     * @param userName
     * @return ArrayList<UserPackageEntity>
     */
    @Override
    public ArrayList<UserPackageEntity> getPackageByUsername(String userName){
        List<UserPackageEntity> entites = new ArrayList<UserPackageEntity>();
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from UserPackageEntity where username = '"+userName+"'").list();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return (ArrayList<UserPackageEntity>) entites;
    }

    /**
     * 插入一个套餐
     * @param username,pid,effectNow
     * @return ResultMessage
     */
    public ResultMessage insertPackageForUser(String username, int pid, boolean effectNow){
        UserPackageEntity userPackageEntity = new UserPackageEntity();
        userPackageEntity.setUsername(username);
        userPackageEntity.setPid(pid);
        userPackageEntity.setOnEffect((byte) 1);
        java.sql.Date date;
        //根据是否立即生效决定日期和on_effect字段
        if(effectNow){
            date = new java.sql.Date(new java.util.Date().getTime());
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 1);
            date = new java.sql.Date(calendar.getTime().getTime());
        }
        userPackageEntity.setCreationDate(date);
        userPackageEntity.setId(new idHelper().getTableId("UserPackageEntity"));
        //插入结果
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(userPackageEntity);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        finally {
            session.close();
        }
        return ResultMessage.SUCCESS;
    }


    /**
     * 为用户取消一个套餐
     * @param username,pid,effectMonth,effectNow
     * effectMonth代表套餐生效月份，effectNow代表取消操作是立即生效还是下月生效
     * @return
     */
    public ResultMessage updatePackageForUser(String username,int pid,String effectMonth,boolean effectNow){
        List<UserPackageEntity> entites = new ArrayList<UserPackageEntity>();
        List<UserPackageEntity> effectEntites = new ArrayList<UserPackageEntity>(); //存储有效的套餐信息
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from UserPackageEntity where username = '"+username+"'"
            +" and pid = "+pid).list();
            for(int i=0;i<entites.size();i++){
                Date date = entites.get(i).getCreationDate();
                String month = date.toString().split("-")[1]; //获取date中的月份
                if(month.equals(effectMonth)){
                    effectEntites.add(entites.get(i));
                }
            }
            //获取当前月份
            Calendar cal=Calendar.getInstance();
            int currentMonth = cal.get(Calendar.MONTH);
            if((currentMonth+"").equals(effectMonth)){ //处理的是当前月份的套餐
                if(effectNow){
                    for(int i=0;i<effectEntites.size();i++){
                        effectEntites.get(i).setOnEffect((byte)0);
                    }
                }
            }
            if(!(currentMonth+"").equals(effectMonth)){//处理的是下个月的套餐
                for(int i=0;i<effectEntites.size();i++){
                    effectEntites.get(i).setOnEffect((byte)0);
                }
            }
            for(int i=0;i<effectEntites.size();i++){
                session.update(effectEntites.get(i));
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        finally {
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 根据用户姓名查询本月所有有效套餐
     * @param username,month
     * @return List<UserPackageEntity>
     */
    public ArrayList<UserPackageEntity> searchEffectPackageByUserAndMonth(String username,String month){
        List<UserPackageEntity> entites = new ArrayList<UserPackageEntity>();
        List<UserPackageEntity> effectEntites = new ArrayList<UserPackageEntity>(); //存储有效的套餐信息
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from UserPackageEntity where username = '"+username+"'"+"and onEffect = 1").list();
            for(int i=0;i<entites.size();i++){
                Date date = entites.get(i).getCreationDate();
                String tempMonth = date.toString().split("-")[1]; //获取date中的月份
                if(month.equals(tempMonth)){
                    effectEntites.add(entites.get(i));
                }
            }
            for(int i=0;i<effectEntites.size();i++){
                System.out.println(effectEntites.get(i).getId());
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return (ArrayList<UserPackageEntity>) entites;
    }

    public static void main(String[] args) {
        UserPackageEntityDaoImpl u = new UserPackageEntityDaoImpl();
        System.out.println(u.getPackageByUsername("Albert"));
        //u.insertPackageForUser("Mary",1,false);
        DiscountRecordEntityDaoImpl d = new DiscountRecordEntityDaoImpl();
        Map map = d.totalPackageDiscount(u.searchEffectPackageByUserAndMonth("James","10"));
        System.out.println(map.get("phoneAmount"));
        System.out.println(map.get("localDataAmount"));
        System.out.println(map.get("domesticDataAmount"));
        System.out.println(map.get("messageAmount"));
        System.out.println(map.get("baseAmount"));
    }
}
