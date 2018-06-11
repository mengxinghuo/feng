package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.AdminBank;
import com.truck.vo.AdminBankListVo;

public interface IAdminBankService {

    ServerResponse add(Integer adminId, AdminBank bank);

    ServerResponse del(Integer adminId, Integer bankId);

    ServerResponse update(Integer adminId, AdminBank bank);

    ServerResponse<AdminBankListVo> select(Integer adminId, Integer bankId);

    ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize);
}
