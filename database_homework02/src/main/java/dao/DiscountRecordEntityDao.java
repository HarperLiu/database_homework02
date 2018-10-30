package dao;

import entites.UserPackageEntity;

import java.util.ArrayList;
import java.util.HashMap;

public interface DiscountRecordEntityDao {
    /**
     * 根据套餐列表返回所有套餐的优惠通话时长总和、国内流量总和、本地流量总和
     * @param packageEntities
     * @return Hashmap
     */
    public HashMap totalPackageDiscount(ArrayList<UserPackageEntity> packageEntities);


}
