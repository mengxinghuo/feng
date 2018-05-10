package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.CompanyContact;

public interface ICompanyContactService {

    ServerResponse add(Integer userId,CompanyContact companyContact);

    ServerResponse del(Integer companyContactId);

    ServerResponse update(CompanyContact companyContact);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
