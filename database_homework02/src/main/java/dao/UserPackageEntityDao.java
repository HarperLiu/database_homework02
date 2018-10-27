/**
 * created by harperliu on 10.27
 */
package dao;

import entites.UserPackageEntity;
import java.util.ArrayList;


public interface UserPackageEntityDao {
    /**
     * 根据用户姓名查找所有优惠套餐
     * @param username
     * @return ArrayList<UserPackageEntity>
     */
    public ArrayList<UserPackageEntity> getPackageByUsername(String username);
}
