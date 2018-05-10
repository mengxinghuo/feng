package com.truck.dao;

import com.truck.pojo.CompanyContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyContactMapper {
    int deleteByPrimaryKey(Integer companyContactId);

    int insert(CompanyContact record);

    int insertSelective(CompanyContact record);

    CompanyContact selectByPrimaryKey(Integer companyContactId);

    int updateByPrimaryKeySelective(CompanyContact record);

    int updateByPrimaryKey(CompanyContact record);

    List<CompanyContact> selectByCompanyId(@Param("companyId") Integer companyId);
}