import dao.*;
import entites.UserPackageEntity;
import utils.ResultMessage;
import utils.outputHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by harperliu on 10.26
 */

public class Main {
    static final UserPackageEntityDao userPackageImpl = new UserPackageEntityDaoImpl();
    static final PhoneRecordEntityDao phoneRecordImpl = new PhoneRecordEntityDaoImpl();
    static final DataRecordEntityDao dataRecordImpl = new DataRecordEntityDaoImpl();
    static final DiscountRecordEntityDao discountRecordImpl = new DiscountRecordEntityDaoImpl();
    static final outputHelper helper = new outputHelper();
    static final double phoneFee = 0.5;
    static final double messageFee = 0.1;
    static final int localDataFee = 2;
    static final int domesticDataFee  = 5;
    public static void main(String[] args) {
        Main main = new Main();

        main.searchPackageByUser("Albert");
        main.orderPakageForUser("Albert",2,true);
        main.searchPackageByUser("Albert");
        main.cancelPackageForUser("Mary",1,"11",true);
        main.generatePhoneRecord("Mary",36);
        main.generateDataRecord("Mary",100,"local");
        long start = new Date().getTime();
        main.formMonthBillForUser("James","10");
        long end = new Date().getTime();
        System.out.println("操作用时"+(end-start)+"ms");
    }

    /**
     * 根据用户名查询用户套餐（包括历史套餐）
     * @param username
     * @return
     */
    public void searchPackageByUser(String username){
        List<UserPackageEntity> packages = userPackageImpl.getPackageByUsername(username);
        helper.printPackageByUsername(packages,username);
    }

    /**
     * 为用户订购一个套餐
     * @param username,pid,effectNow
     * @return
     */
    public void orderPakageForUser(String username,int pid, boolean effectNow){
        ResultMessage resultMessage = userPackageImpl.insertPackageForUser(username,pid,effectNow);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            System.out.println("订购套餐成功!");
        }else{
            System.out.println("订购套餐失败，请重试！");
        }
    }

    /**
     * 为用户取消一个套餐
     * @param username,pid,effectMonth,effectNow
     * effectMonth代表套餐生效月份，effectNow代表取消操作是立即生效还是下月生效
     * @return
     */
    public void cancelPackageForUser(String username,int pid,String effectMonth,boolean effectNow){
        ResultMessage resultMessage = userPackageImpl.updatePackageForUser(username,pid,effectMonth,effectNow);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            System.out.println("取消套餐成功!");
        }else{
            System.out.println("取消套餐失败，请重试！");
        }
    }

    /**
     * 为用户生成一条通话资费记录
     * @param username,time
     * @return
     */
    public void generatePhoneRecord(String username,int time){
        ResultMessage resultMessage = phoneRecordImpl.insertPhoneRecord(username,time);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            System.out.println("生成通话记录成功!");
        }else{
            System.out.println("生成通话记录失败，请重试！");
        }
    }

    /**
     * 为用户生成一条流量资费记录
     * @param username,dataUsage,dataType
     * @return
     */
    public void generateDataRecord(String username,int dataUsage,String dataType){
        ResultMessage resultMessage = dataRecordImpl.insertDataRecord(username,dataUsage,dataType);
        if(resultMessage.equals(ResultMessage.SUCCESS)){
            System.out.println("生成流量记录成功!");
        }else{
            System.out.println("生成流量记录失败，请重试！");
        }
    }

    /**
     * 生成用户月账单
     * @param username,month
     * @return
     */
    public void formMonthBillForUser(String username,String month){
        int phoneCallAmount = phoneRecordImpl.searchPhoneCallAmountByUser(username,month);
        int localDataAmount = dataRecordImpl.searchLocalDataAmmountByUser(username,month);
        int domesticAmount = dataRecordImpl.searchDomesticDataAmmountByUser(username,month);
        int messageAmount = (int)(Math.random()*1000+1); //短信数量随机生成
        Map map = discountRecordImpl.totalPackageDiscount(userPackageImpl.searchEffectPackageByUserAndMonth(username,month));
        int packagePhoneAmount = (int)map.get("phoneAmount");
        int packageLocalDataAmount = (int)(map.get("localDataAmount"));
        int packageDomesticDataAmount = (int)(map.get("domesticDataAmount"));
        int packageMessageAmount = (int)(map.get("messageAmount"));
        int packageBaseAmount = (int)(map.get("baseAmount"));
        double totalPhoneCallFee = 0.0;
        double totalMessageFee = 0.0;
        double totalLocalDataFee = 0.0;
        double totalDomesticDataFee = 0.0;
        double totalFee = 0.0;
        //计算账单金额
        if(phoneCallAmount>packageBaseAmount){
            totalPhoneCallFee = phoneFee*(phoneCallAmount-packagePhoneAmount);
        }
        if(messageAmount>packageMessageAmount){
            totalMessageFee = messageFee*(messageAmount-packageMessageAmount);
        }
        if(localDataAmount>packageLocalDataAmount){
            totalLocalDataFee = localDataFee*(localDataAmount-packageLocalDataAmount);
        }
        if(domesticAmount>packageDomesticDataAmount){
            totalDomesticDataFee = domesticDataFee*(domesticAmount-packageDomesticDataAmount);
        }
        totalFee = packageBaseAmount+totalPhoneCallFee+totalMessageFee+totalLocalDataFee+totalDomesticDataFee;
        map.put("totalPhoneCall",phoneCallAmount);
        map.put("totalMessage",messageAmount);
        map.put("totalLocalData",localDataAmount);
        map.put("totalDomesticData",domesticAmount);
        map.put("packagePhoneCall",packagePhoneAmount);
        map.put("packageMessage",packageMessageAmount);
        map.put("packageLocalData",packageLocalDataAmount);
        map.put("packageDomesticData",packageDomesticDataAmount);
        map.put("totalFee",totalFee);
        helper.printBillByUserAndMonth(username,month, (HashMap) map);
    }
}
