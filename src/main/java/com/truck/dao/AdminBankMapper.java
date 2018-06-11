package com.truck.dao;

import com.truck.pojo.AdminBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminBankMapper {
    int deleteByPrimaryKey(Integer bankId);

    int insert(AdminBank record);

    int insertSelective(AdminBank record);

    AdminBank selectByPrimaryKey(Integer bankId);

    int updateByPrimaryKeySelective(AdminBank record);

    int updateByPrimaryKey(AdminBank record);

    int deleteByBankIdAdminId(@Param("adminId") Integer adminId, @Param("bankId") Integer bankId);

    int updateByBank(AdminBank record);

    AdminBank selectByBankIdAdminId(@Param("adminId") Integer adminId, @Param("bankId") Integer bankId);

    List<AdminBank> selectByAdminId(@Param("adminId") Integer adminId);
}