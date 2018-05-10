package com.truck.dao;

import com.truck.pojo.BalanceAlteration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BalanceAlterationMapper {
    int deleteByPrimaryKey(Integer balanceAlterationId);

    int insert(BalanceAlteration record);

    int insertSelective(BalanceAlteration record);

    BalanceAlteration selectByPrimaryKey(Integer balanceAlterationId);

    int updateByPrimaryKeySelective(BalanceAlteration record);

    int updateByPrimaryKey(BalanceAlteration record);

    List<BalanceAlteration> selectAllBalanceAlteration(@Param("status") Integer status);

    List<BalanceAlteration> selectBalanceAlterationByUserInfo(@Param("userInfoId") Integer userInfoId, @Param("status") Integer status);

    List<BalanceAlteration> selectByUserInfo(@Param("userInfoId") Integer userInfoId);

    int deleteByUserInfoId(@Param("userInfoId") Integer userInfoId);
}