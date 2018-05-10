package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ProductConfMapper;
import com.truck.pojo.ProductComponent;
import com.truck.pojo.ProductConf;
import com.truck.service.IProductConfService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.ProductComponentVo;
import com.truck.vo.ProductConfVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("productConfService")
public class IProductConfServiceImpl implements IProductConfService {

    private Logger logger = LoggerFactory.getLogger(IProductConfServiceImpl.class);

    @Autowired
    private ProductConfMapper productConfMapper;

    private ProductConfVo assembleProductConfVo(ProductConf productConf){
        ProductConfVo productConfVo = new ProductConfVo();
        productConfVo.setId(productConf.getId());
        productConfVo.setProductId(productConf.getProductId());
        productConfVo.setComponentId(productConf.getComponentId());
        productConfVo.setParamName(productConf.getParamName());
        productConfVo.setParamValue(productConf.getParamValue());
        return productConfVo;
    }


    private ProductConf assembleProductConf(ProductConfVo productConfVo){
       ProductConf productConf = new ProductConf();
        productConf.setProductId(productConfVo.getProductId());
        productConf.setComponentId(productConfVo.getComponentId());
        productConf.setParamName(productConfVo.getParamName());
        productConf.setParamValue(productConfVo.getParamValue());
        return productConf;
    }

    public ServerResponse<PageInfo> listConfByProductId(Integer productId, Integer componentId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductConf> productConfList = productConfMapper.listConfByProductId(productId ,componentId);
        List<ProductConfVo> productConfVoList = Lists.newArrayList();
        for (ProductConf productConf: productConfList) {
            productConfVoList.add(assembleProductConfVo(productConf));
        }
        PageInfo pageResult = new PageInfo(productConfList);
        pageResult.setList(productConfVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse add(ProductConfVo productConfVo) {
        ProductConf productConf = assembleProductConf(productConfVo);
        int rowCount = productConfMapper.insert(productConf);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("id", productConf.getId());
            return ServerResponse.createBySuccess("新建属性成功", result);
        }
        return ServerResponse.createByErrorMessage("新建属性失败");
    }

    public ServerResponse<String> del( Integer id) {
        int resultCount = productConfMapper.deleteById(id);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除组件信息成功");
        }
        return ServerResponse.createByErrorMessage("删除组件信息失败");
    }


    public ServerResponse update( ProductConf productConf) {
        int rowCount = productConfMapper.updateByProductConf(productConf);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新组件信息成功");
        }
        return ServerResponse.createByErrorMessage("更新组件信息失败");
    }
}


