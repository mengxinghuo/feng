package com.truck.dao;

import com.truck.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectCountByUserId(@Param("userId") Integer userId);

    List<UserInfo> selectAllUserInfo(@Param("companyName") String companyName);

    int deleteByUserId(@Param("userId") Integer userId);

    UserInfo selectUserInfoByDutyId(@Param("dutyId") String dutyId);

    UserInfo selectByDutyIdExcept(@Param("userId") Integer userId, @Param("dutyId") String dutyId);
}