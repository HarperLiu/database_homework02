/**
 * created by harperliu on 10.27
 */
package dao;

import entites.UserPackageEntity;
import utils.ResultMessage;

import java.util.ArrayList;


public interface UserPackageEntityDao {
    /**
     * 根据用户姓名查找所有优惠套餐
     * @param username
     * @return ArrayList<UserPackageEntity>
     */
    public ArrayList<UserPackageEntity> getPackageByUsername(String username);

    /**
     * 插入一个套餐
     * @param username,pid,effectNow
     * @return ResultMessage
     */
    public ResultMessage insertPackageForUser(String username,int pid,boolean effectNow);

    /**
     * 为用户取消一个套餐
     * @param username,pid,effectMonth,effectNow
     * effectMonth代表套餐生效月份，effectNow代表取消操作是立即生效还是下月生效
     * @return
     */
    public ResultMessage updatePackageForUser(String username,int pid,String effectMonth,boolean effectNow);

    /**
     * 根据用户姓名查询本月所有有效套餐
     * @param username,month
     * @return List<UserPackageEntity>
     */
    public ArrayList<UserPackageEntity> searchEffectPackageByUserAndMonth(String username,String month);
}
