package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.StockAlteration;
import com.truck.vo.StockAlterationVo;

public interface IStockAlterationService {

    ServerResponse stockAlterationList(Integer adminId,int pageNum, int pageSize, Integer status);

    StockAlterationVo assembleStockAlterationVo(StockAlteration stockAlteration);

    ServerResponse getListByProductId(Integer adminId,Integer productId,Integer warehouseId, Integer status,String searchDate,String beginDate,String endDate,int pageNum, int pageSize);
}
