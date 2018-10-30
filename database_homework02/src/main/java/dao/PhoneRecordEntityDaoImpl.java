package dao;

import entites.PhoneRecordEntity;
import entites.UserPackageEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;
import utils.ResultMessage;
import utils.idHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PhoneRecordEntityDaoImpl implements PhoneRecordEntityDao {
    /**
     * 插入一条通话资费记录
     * @param username,time
     * @return
     */
    public ResultMessage insertPhoneRecord(String username, int time){
        PhoneRecordEntity phoneRecordEntity = new PhoneRecordEntity();
        phoneRecordEntity.setUsername(username);
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        phoneRecordEntity.setCreationDate(date);
        phoneRecordEntity.setLastTime(time);
        phoneRecordEntity.setId(new idHelper().getTableId("PhoneRecordEntity"));
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(phoneRecordEntity);
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
     * 查询用户本月所有通话记录
     * @param username month
     * @return phoneCallAmount
     */
    public int searchPhoneCallAmountByUser(String username, String month){
        List<PhoneRecordEntity> entites = new ArrayList<PhoneRecordEntity>();
        List<PhoneRecordEntity> effectEntites = new ArrayList<PhoneRecordEntity>(); //存储有效的套餐信息
        int amount = 0;
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from PhoneRecordEntity where username = '"+username+"'").list();
            for(int i=0;i<entites.size();i++){
                Date date = entites.get(i).getCreationDate();
                String tempMonth = date.toString().split("-")[1]; //获取date中的月份
                if(month.equals(tempMonth)){
                    effectEntites.add(entites.get(i));
                }
            }
            for(int j=0;j<effectEntites.size();j++){
                amount+=effectEntites.get(j).getLastTime();
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return amount;
    }

    public static void main(String[] args) {
        PhoneRecordEntityDaoImpl p = new PhoneRecordEntityDaoImpl();
        System.out.println(p.searchPhoneCallAmountByUser("Albert","11"));
    }
}
