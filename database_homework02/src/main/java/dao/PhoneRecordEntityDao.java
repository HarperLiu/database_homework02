package dao;

import utils.ResultMessage;

public interface PhoneRecordEntityDao {
    /**
     * 插入一条通话资费记录
     * @param username,time
     * @return ResultMessage
     */
    public ResultMessage insertPhoneRecord(String username, int time);

    /**
     * 查询用户本月所有通话记录
     * @param username month
     * @return phoneCallAmount
     */
    public int searchPhoneCallAmountByUser(String username, String month);

}
