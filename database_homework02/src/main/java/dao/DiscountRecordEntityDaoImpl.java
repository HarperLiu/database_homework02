package dao;

import entites.DiscountPackageEntity;
import entites.UserPackageEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountRecordEntityDaoImpl implements DiscountRecordEntityDao {
    /**
     * 根据套餐列表返回所有套餐的优惠通话时长总和、国内流量总和、本地流量总和、短信数量总和、月功能费总和
     * @param packageEntities
     * @return HashMap
     */
    public HashMap totalPackageDiscount(ArrayList<UserPackageEntity> packageEntities){
        int packagePhoneCallAmount = 0;
        int packageDomesticDataAmount = 0;
        int packageLocalDataAmount = 0;
        int packageMessageAmount = 0;
        int packageBaseAmount = 0;
        Map map = new HashMap();
        List<DiscountPackageEntity> entites = new ArrayList<DiscountPackageEntity>();
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            entites = session.createQuery("from DiscountPackageEntity").list();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        for(int i=0;i<packageEntities.size();i++){
            for(int j=0;j<entites.size();j++){
                if(packageEntities.get(i).getPid()==entites.get(j).getPid()){
                    packagePhoneCallAmount+=entites.get(j).getPhone();
                    packageDomesticDataAmount+=entites.get(j).getDomesticData();
                    packageLocalDataAmount+=entites.get(j).getLocalData();
                    packageMessageAmount+=entites.get(j).getMessage();
                    packageBaseAmount+=entites.get(j).getBase();
                }
            }
        }
        map.put("phoneAmount",packagePhoneCallAmount);
        map.put("localDataAmount",packageLocalDataAmount);
        map.put("domesticDataAmount",packageDomesticDataAmount);
        map.put("messageAmount",packageMessageAmount);
        map.put("baseAmount",packageBaseAmount);
        return (HashMap) map;
    }


}
