package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Warehouse;
import com.truck.vo.WarehouseVo;

public interface IWarehouseService {

    ServerResponse add(Integer adminId,Warehouse warehouse);

    ServerResponse del(Integer warehouseId);

    ServerResponse update(Warehouse warehouse);

    ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize);

    WarehouseVo assembleWarehouseVo(Warehouse warehouse , Integer productId);
}
