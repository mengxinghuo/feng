package com.truck.dao;

import com.truck.pojo.Bank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankMapper {
    int deleteByPrimaryKey(Integer bankId);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Integer bankId);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    int deleteByBankIdUserId(@Param("userId") Integer uerId, @Param("bankId") Integer bankId);

    int updateByBank(Bank record);

    Bank selectByBankIdUserId(@Param("userId") Integer uerId, @Param("bankId") Integer bankId);

    List<Bank> selectByUserId(@Param("userId") Integer uerId);
}