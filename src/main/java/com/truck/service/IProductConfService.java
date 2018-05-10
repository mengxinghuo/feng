package com.truck.service;


import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.ProductConf;
import com.truck.vo.ProductConfVo;


/**
 * Created by geely
 */
public interface IProductConfService {


    ServerResponse<PageInfo> listConfByProductId(Integer productId, Integer componentId, int pageNum, int pageSize);

    ServerResponse add(ProductConfVo productConfVo);
    ServerResponse<String> del( Integer id);
    ServerResponse update( ProductConf productConf);

}
