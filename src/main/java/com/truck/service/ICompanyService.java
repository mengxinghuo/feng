package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Company;
import com.truck.vo.CompanyDetailVo;

/**
 * Created by geely
 */
public interface ICompanyService {

    ServerResponse saveOrUpdateCompany(Integer userId, Company company, String dutyId, String officeAddress, String companyFax, String companyContactor);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<PageInfo> getCompanyList(int pageNum, int pageSize);

    ServerResponse<PageInfo> getCompanyList(Integer userId, int pageNum, int pageSize);

    ServerResponse<PageInfo> searchCompany(String companyName, Integer companyId, int pageNum, int pageSize);

    ServerResponse<PageInfo> searchCompany(Integer userId, String companyName, Integer companyId, int pageNum, int pageSize);

    ServerResponse<CompanyDetailVo> getCompanyDetail(Integer companyId);

    ServerResponse<CompanyDetailVo> getCompanyDetailSelf(Integer userId);

    ServerResponse<String> deleteByUserId(Integer userId);

}
