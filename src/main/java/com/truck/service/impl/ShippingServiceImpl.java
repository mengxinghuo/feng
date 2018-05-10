package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ShippingMapper;
import com.truck.pojo.Shipping;
import com.truck.service.IShippingService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.ShippingListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    public ServerResponse add(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insertSelective(shipping);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getShippingId());
            return ServerResponse.createBySuccess("新建地址成功",result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    public ServerResponse<String> del(Integer userId, Integer shippingId){
        int rowCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    public ServerResponse update(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    public ServerResponse<ShippingListVo> select(Integer userId, Integer shippingId){
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if(shipping == null){
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        ShippingListVo shippingListVo = assembleShippingListVo(shipping);
        return ServerResponse.createBySuccess("查询成功",shippingListVo);
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        List<ShippingListVo> shippingListVoList = Lists.newArrayList();
        for(Shipping shippingItem : shippingList){
            ShippingListVo shippingListVo = assembleShippingListVo(shippingItem);
            shippingListVoList.add(shippingListVo);
        }
        PageInfo pageInfo = new PageInfo(shippingList);
        pageInfo.setList(shippingListVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    private ShippingListVo assembleShippingListVo(Shipping shipping){
        ShippingListVo shippingListVo = new ShippingListVo();
        shippingListVo.setShippingId(shipping.getShippingId());
        shippingListVo.setUserId(shipping.getUserId());
        shippingListVo.setReceiverName(shipping.getReceiverName());
        shippingListVo.setReceiverPhone(shipping.getReceiverPhone());
        shippingListVo.setReceiverMobile(shipping.getReceiverMobile());
        shippingListVo.setreceiverProvince(shipping.getReceiverProvince());
        shippingListVo.setReceiverCity(shipping.getReceiverCity());
        shippingListVo.setReceiverDistrict(shipping.getReceiverDistrict());
        shippingListVo.setReceiverAddress(shipping.getReceiverAddress());
        shippingListVo.setReceiverZip(shipping.getReceiverZip());
        shippingListVo.setCreateTime(DateTimeUtil.dateToStr(shipping.getCreateTime()));
        shippingListVo.setUpdateTime(DateTimeUtil.dateToStr(shipping.getUpdateTime()));
        return shippingListVo;
    }


}
