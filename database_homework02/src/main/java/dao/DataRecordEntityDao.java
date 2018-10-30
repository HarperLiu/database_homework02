package dao;

import utils.ResultMessage;

public interface DataRecordEntityDao {
    /**
     * 插入一条流量资费记录
     * @param username,dataUsage,dataType
     * @return ResultMessage
     */
    public ResultMessage insertDataRecord(String username,int dataUsage,String dataType);

    /**
     * 查询用户本月所有本地流量记录
     * @param username,month
     * @return localDataAmount
     */
    public int searchLocalDataAmmountByUser(String username,String month);

    /**
     * 查询用户本月所有国内流量记录
     * @param username,month
     * @return localDataAmount
     */
    public int searchDomesticDataAmmountByUser(String username,String month);
}
