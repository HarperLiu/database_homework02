package dao;

import entites.DataRecordEntity;
import entites.PhoneRecordEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;
import utils.ResultMessage;
import utils.idHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DataRecordEntityDaoImpl implements DataRecordEntityDao{
    /**
     * 插入一条流量资费记录
     * @param username,dataUsage,dataType
     * @return ResultMessage
     */
    public ResultMessage insertDataRecord(String username, int dataUsage, String dataType){
        DataRecordEntity dataRecordEntity = new DataRecordEntity();
        dataRecordEntity.setUsername(username);
        dataRecordEntity.setDataUsage(dataUsage);
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        dataRecordEntity.setCreationDate(date);
        dataRecordEntity.setDataType(dataType);
        dataRecordEntity.setId(new idHelper().getTableId("DataRecordEntity"));

        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(dataRecordEntity);
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
     * 查询用户本月所有本地流量记录
     * @param username,month
     * @return localDataAmount
     */
    public int searchLocalDataAmmountByUser(String username,String month){
        List<DataRecordEntity> entites = new ArrayList<DataRecordEntity>();
        List<DataRecordEntity> effectEntites = new ArrayList<DataRecordEntity>(); //存储有效的套餐信息
        int amount = 0;
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from DataRecordEntity where username = '"+username+"'"+"and dataType = 'local'").list();
            for(int i=0;i<entites.size();i++){
                Date date = entites.get(i).getCreationDate();
                String tempMonth = date.toString().split("-")[1]; //获取date中的月份
                if(month.equals(tempMonth)){
                    effectEntites.add(entites.get(i));
                }
            }
            for(int j=0;j<effectEntites.size();j++){
                amount+=effectEntites.get(j).getDataUsage();
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

    /**
     * 查询用户本月所有国内流量记录
     * @param username,month
     * @return localDataAmount
     */
    public int searchDomesticDataAmmountByUser(String username,String month){
        List<DataRecordEntity> entites = new ArrayList<DataRecordEntity>();
        List<DataRecordEntity> effectEntites = new ArrayList<DataRecordEntity>(); //存储有效的套餐信息
        int amount = 0;
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from DataRecordEntity where username = '"+username+"'"+"and dataType = 'domestic'").list();
            for(int i=0;i<entites.size();i++){
                Date date = entites.get(i).getCreationDate();
                String tempMonth = date.toString().split("-")[1]; //获取date中的月份
                if(month.equals(tempMonth)){
                    effectEntites.add(entites.get(i));
                }
            }
            for(int j=0;j<effectEntites.size();j++){
                amount+=effectEntites.get(j).getDataUsage();
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
        DataRecordEntityDaoImpl d = new DataRecordEntityDaoImpl();
        System.out.println(d.searchLocalDataAmmountByUser("Albert","10"));
        System.out.println(d.searchDomesticDataAmmountByUser("Albert","10"));
    }
}
