package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Stock;
import com.truck.vo.StockVo;

/**
 * Created by geely
 */
public interface IStockService {

    ServerResponse saveOrUpdateStock(Integer adminId,Stock stock,String vendor,String buyingContract);

    ServerResponse<String> setStockStatus(Integer stockId, Integer status);

    ServerResponse<String> reduceStockNum(Integer stockId, Integer reduceNum);

    ServerResponse<String> addStockNum(Integer stockId, Integer addNum);

//    ServerResponse<StockVo> manageStockDetail(Integer stockId);

    ServerResponse selectListByProductIdWarehouseId(Integer adminId,Integer productId,String idCode,Integer warehouseId,Integer stockStatus,int pageNum, int pageSize);

    ServerResponse listByJingJie(Integer adminId,Integer productId,String idCode,Integer warehouseId,Integer stockStatus,int pageNum, int pageSize);

    ServerResponse listByNumWarehouse(Integer adminId,Integer productId,String idCode,Integer warehouseId,Integer stockStatus,int pageNum, int pageSize);

//    ServerResponse<PageInfo> searchStock(String stockName, Integer stockId, int pageNum, int pageSize);

    ServerResponse<StockVo> getStockDetail(Integer stockId);

//    ServerResponse<PageInfo> getStockByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);

    StockVo assembleStockVo(Stock stock);

}
