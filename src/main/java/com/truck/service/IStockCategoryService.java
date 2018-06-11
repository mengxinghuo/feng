package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.StockCategory;

import java.util.List;

/**
 * Created by geely
 */
public interface IStockCategoryService {
    ServerResponse addStockCategory(Integer adminId ,String categoryName, Integer parentId);
    ServerResponse updateStockCategoryName(Integer categoryId, String categoryName);
    ServerResponse<List<StockCategory>> getChildrenParallelStockCategory(Integer categoryId,Integer warehouseId);
    ServerResponse<List<Integer>> selectStockCategoryAndChildrenById(Integer categoryId);

    ServerResponse selectStockCategoryObjectAndChildrenById(Integer categoryId);

    ServerResponse deleteById(Integer categoryId);

}
