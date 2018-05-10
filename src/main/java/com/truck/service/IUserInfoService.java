package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Company;
import com.truck.pojo.UserInfo;
import com.truck.vo.UserInfoVo;

import java.math.BigDecimal;

public interface IUserInfoService {

    ServerResponse addUserInfo(Integer userId, Company company, String dutyId, String officeAddress, String companyFax, String companyContactor);

    ServerResponse<String> updateUserInfo(Integer userId, Company company, String dutyId, String officeAddress, String companyFax, String companyContactor);

    ServerResponse<UserInfoVo> getUserInfo(Integer userId);

    //manage
    ServerResponse<PageInfo> getAllUserInfo(String companyName, int pageNum, int pageSize);

    ServerResponse<UserInfoVo> searchByUserId(Integer userId);

    ServerResponse rechargeBalance(Integer userInfoId, BigDecimal money);

    ServerResponse getBalanceAlteration(Integer userId, int pageNum, int pageSize);
}
