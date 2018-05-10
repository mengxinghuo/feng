package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ProductComponentMapper;
import com.truck.dao.ProductConfMapper;
import com.truck.pojo.ProductComponent;
import com.truck.pojo.ProductConf;
import com.truck.service.IProductComponentService;
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
@Service("productComponentService")
public class IProductComponentServiceImpl implements IProductComponentService {

    private Logger logger = LoggerFactory.getLogger(IProductComponentServiceImpl.class);

    @Autowired
    private ProductComponentMapper productComponentMapper;
    @Autowired
    private ProductConfMapper productConfMapper;

    private ProductComponentVo assembleProductComponentVo(ProductComponent productComponent,List<ProductConfVo> productConfVoList){
        ProductComponentVo productComponentVo = new ProductComponentVo();
        productComponentVo.setComponentId(productComponent.getComponentId());
        productComponentVo.setName(productComponent.getName());
        productComponentVo.setProductId(productComponent.getProductId());
        productComponentVo.setProductConfVoList(productConfVoList);
        productComponentVo.setCreateTime(DateTimeUtil.dateToStr(productComponent.getCreateTime()));
        productComponentVo.setUpdateTime(DateTimeUtil.dateToStr(productComponent.getUpdateTime()));
        return productComponentVo;
    }

    private List<ProductConfVo> assembleProductConfVo(List<ProductConf> productConfList){
        List<ProductConfVo> productConfVoList = Lists.newArrayList();
        for(ProductConf productConf : productConfList) {
            ProductConfVo productConfVo = new ProductConfVo();
            productConfVo.setId(productConf.getId());
            productConfVo.setProductId(productConf.getProductId());
            productConfVo.setComponentId(productConf.getComponentId());
            productConfVo.setParamName(productConf.getParamName());
            productConfVo.setParamValue(productConf.getParamValue());
            productConfVoList.add(productConfVo);
        }
        return productConfVoList;
    }

    public ServerResponse<PageInfo> listComponentByProductId(Integer productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductComponent> productComponentList = productComponentMapper.listComponentByProductId(productId);
        List<ProductComponentVo> productComponentVoList = Lists.newArrayList();
        for (ProductComponent productComponent: productComponentList) {
            List<ProductConfVo> productConfVoList = assembleProductConfVo(productConfMapper.listConfByProductId(productId ,
                                                                        productComponent.getComponentId()));
            productComponentVoList.add(assembleProductComponentVo(productComponent,productConfVoList));
        }
        PageInfo pageResult = new PageInfo(productComponentList);
        pageResult.setList(productComponentVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse add( ProductComponent productComponent) {
        int rowCount = productComponentMapper.insert(productComponent);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("componentId", productComponent.getComponentId());
            return ServerResponse.createBySuccess("新建组件信息成功", result);
        }
        return ServerResponse.createByErrorMessage("新建组件信息成功");
    }

    public ServerResponse<String> del( Integer componentId) {
        int resultCount = productComponentMapper.deleteByComponentId( componentId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除组件信息成功");
        }
        return ServerResponse.createByErrorMessage("删除组件信息失败");
    }


    public ServerResponse update( ProductComponent productComponent) {
        int rowCount = productComponentMapper.updateByProductComponent(productComponent);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新组件信息成功");
        }
        return ServerResponse.createByErrorMessage("更新组件信息失败");
    }
}


