package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Bank;
import com.truck.vo.BankListVo;

public interface IBankService {

    ServerResponse add(Integer userId, Bank bank);

    ServerResponse del(Integer userId, Integer bankId);

    ServerResponse update(Integer userId, Bank bank);

    ServerResponse<BankListVo> select(Integer userId, Integer bankId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
