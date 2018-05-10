package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.CompanyAddress;

public interface ICompanyAddressService {

    ServerResponse add(Integer userId,CompanyAddress companyAddress);

    ServerResponse del(Integer companyAddressId);

    ServerResponse update(CompanyAddress companyAddress);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
