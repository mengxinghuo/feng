package com.truck.dao;

import com.truck.pojo.CompanyAddress;
import com.truck.pojo.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyAddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(CompanyAddress record);

    int insertSelective(CompanyAddress record);

    CompanyAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(CompanyAddress record);

    int updateByPrimaryKey(CompanyAddress record);

    List<CompanyAddress> selectByCompanyId(@Param("companyId") Integer companyId);
}