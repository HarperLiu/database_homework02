/**
 * created by harperliu on 10.27
 */
package utils;

import entites.UserPackageEntity;

import java.util.HashMap;
import java.util.List;

public class outputHelper {
    /**
     * 根据用户名打印用户的所有套餐记录（包括历史套餐）
     * @param list, username
     * @return
     */
    public void printPackageByUsername(List<UserPackageEntity> list,String username){
        StringBuilder sb = new StringBuilder();
        sb.append(username+"用户的所有套餐记录如下："+"\n");
        for(int i=0;i<list.size();i++){
            UserPackageEntity entity = list.get(i);
            sb.append("id: "+entity.getId()+","+"creation_date: "+entity.getCreationDate()+","+"pid: "+entity.getPid()+","+"on_effect: "+entity.getOnEffect()+"\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * 根据用户名和月份打印用户月账单
     * @param username,month,hashmap
     * @return
     */
    public void printBillByUserAndMonth(String username, String month, HashMap data){
        StringBuilder sb = new StringBuilder();
        sb.append("尊敬的"+username+"用户，您"+month+"月账单如下："+"\n");
        sb.append("总通话时长："+data.get("totalPhoneCall")+"\n");
        sb.append("总短信条数："+data.get("totalMessage")+"\n");
        sb.append("本地流量使用："+data.get("totalLocalData")+"\n");
        sb.append("国内流量使用："+data.get("totalDomesticData")+"\n");
        sb.append("套餐内通话时长："+data.get("packagePhoneCall")+"\n");
        sb.append("套餐内短信："+data.get("packageMessage")+"\n");
        sb.append("套餐内本地流量"+data.get("packageLocalData")+"\n");
        sb.append("套餐内国内流量"+data.get("packageDomesticData")+"\n");
        sb.append("总资费："+data.get("totalFee"));
        System.out.println(sb.toString());
    }
}
