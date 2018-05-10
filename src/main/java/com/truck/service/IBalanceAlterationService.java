package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.BalanceAlteration;
import com.truck.vo.BalanceAlterationVo;

public interface IBalanceAlterationService {

    ServerResponse balanceAlterationList(int pageNum, int pageSize, Integer status);

    BalanceAlterationVo assembleBalanceAlterationVo(BalanceAlteration balanceAlteration);

    ServerResponse getListByUserInfo(Integer userInfoId, int pageNum, int pageSize, Integer status);
}
