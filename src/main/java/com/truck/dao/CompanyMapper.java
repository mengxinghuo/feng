package com.truck.dao;

import com.truck.pojo.Company;
import com.truck.pojo.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {

    int deleteByPrimaryKey(Integer companyId);

    int deleteByUserId(Integer userId);

    int insert(Company record);

    int selectCompanyIdByuserId(@Param("userId") Integer userId);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectList();

    Company selectByUserId(@Param("userId") Integer userId);

    List<Company> selectByNameAndCompanyId(@Param("companyName") String companyName, @Param("companyId") Integer companyId);

    List<Company> selectByNameAndCompanyIdUserId(@Param("userId") Integer userId, @Param("companyName") String companyName, @Param("companyId") Integer companyId);

    List<Company> selectCompanyListByUserId(@Param("userId") Integer userId);

    List<Company> selectCompanyListByOrderId(@Param("orderId") Integer orderId);

    int checkCompanyName(@Param("companyName") String companyName);

    int checkCompanyEmail(@Param("companyEmail") String companyEmail);

    int checkCompanyNum(@Param("userId") Integer userId);

}