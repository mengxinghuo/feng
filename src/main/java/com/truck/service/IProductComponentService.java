package com.truck.service;


import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.ProductComponent;


/**
 * Created by geely
 */
public interface IProductComponentService {

    ServerResponse<PageInfo> listComponentByProductId(Integer productId,int pageNum, int pageSize);

    ServerResponse add( ProductComponent productComponent);
    ServerResponse<String> del( Integer componentId);
    ServerResponse update( ProductComponent productComponent);

}
