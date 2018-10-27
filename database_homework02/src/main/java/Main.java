import java.util.Date;

/**
 * Created by harperliu on 10.26
 */

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

    }

    /**
     * 根据用户名查询用户套餐（包括历史套餐）
     * @param username
     * @return
     */
    public void searchPackageByUser(String username){}

    /**
     * 为用户订购一个套餐
     * @param username,pid,effectNow
     * @return
     */
    public void orderPakageForUser(String username,int pid, boolean effectNow){}

    /**
     * 为用户取消一个套餐
     * @param username,pid,effectNow
     * @return
     */
    public void cancelPackageForUser(String username,int pid,boolean effectNow){}

    /**
     * 为用户生成一条通话资费记录
     * @param username,time
     * @return
     */
    public void generatePhoneRecord(String username,int time){}

    /**
     * 为用户生成一条流量资费记录
     * @param username,dataUsage,dataType
     * @return
     */
    public void generatePhoneRecord(String username,int dataUsage,String dataType){}

    /**
     * 生成用户月账单
     * @param username,date
     * @return
     */
    public void formMonthBillForUser(String username,Date date){}
}
