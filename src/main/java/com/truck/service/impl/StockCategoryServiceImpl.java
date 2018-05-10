package com.truck.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.StockCategoryMapper;
import com.truck.pojo.StockCategory;
import com.truck.service.IStockCategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by geely
 */
@Service("iStockCategoryService")
public class StockCategoryServiceImpl implements IStockCategoryService {

    private Logger logger = LoggerFactory.getLogger(StockCategoryServiceImpl.class);

    @Autowired
    private StockCategoryMapper stockCategoryMapper;

    public ServerResponse addStockCategory(Integer adminId, String stockCategoryName, Integer parentId){
        if(parentId == null || StringUtils.isBlank(stockCategoryName)){
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        StockCategory stockCategory = new StockCategory();
        stockCategory.setAdminId(adminId);
        stockCategory.setName(stockCategoryName);
        stockCategory.setParentId(parentId);
        stockCategory.setStatus(true);//这个分类是可用的

        int rowCount = stockCategoryMapper.insertSelective(stockCategory);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("categoryId",stockCategory.getId());
            return ServerResponse.createBySuccess("添加品类成功",result);
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    public ServerResponse updateStockCategoryName(Integer stockCategoryId, String stockCategoryName){
        if(stockCategoryId == null || StringUtils.isBlank(stockCategoryName)){
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        StockCategory stockCategory = new StockCategory();
        stockCategory.setId(stockCategoryId);
        stockCategory.setName(stockCategoryName);

        int rowCount = stockCategoryMapper.updateByPrimaryKeySelective(stockCategory);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    public ServerResponse<List<StockCategory>> getChildrenParallelStockCategory(Integer stockCategoryId){
        List<StockCategory> stockCategoryList = stockCategoryMapper.selectStockCategoryChildrenByParentId(stockCategoryId);
        if(CollectionUtils.isEmpty(stockCategoryList)){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(stockCategoryList);
    }


    /**
     * 递归查询本节点的id及孩子节点的id
     * @param stockCategoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectStockCategoryAndChildrenById(Integer stockCategoryId){
        Set<StockCategory> stockCategorySet = Sets.newHashSet();
        findChildStockCategory(stockCategorySet,stockCategoryId);

        List<Integer> stockCategoryIdList = Lists.newArrayList();
        if(stockCategoryId != null){
            for(StockCategory stockCategoryItem : stockCategorySet){
                stockCategoryIdList.add(stockCategoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(stockCategoryIdList);
    }

    /**
     * 递归查询本节点的id及孩子节点的对象
     * @param stockCategoryId
     * @return
     */
    public ServerResponse selectStockCategoryObjectAndChildrenById(Integer stockCategoryId){
        Set<StockCategory> stockCategorySet = Sets.newHashSet();
        findChildStockCategory(stockCategorySet,stockCategoryId);
        return ServerResponse.createBySuccess(stockCategorySet);
    }


    //递归算法,算出子节点
    private Set<StockCategory> findChildStockCategory(Set<StockCategory> stockCategorySet , Integer stockCategoryId){
        StockCategory stockCategory = stockCategoryMapper.selectByPrimaryKey(stockCategoryId);
        if(stockCategory != null){
            stockCategorySet.add(stockCategory);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<StockCategory> stockCategoryList = stockCategoryMapper.selectStockCategoryChildrenByParentId(stockCategoryId);
        for(StockCategory stockCategoryItem : stockCategoryList){
            findChildStockCategory(stockCategorySet,stockCategoryItem.getId());
        }
        return stockCategorySet;
    }

    public ServerResponse deleteById(Integer categoryId) {
        if (StringUtils.isBlank(String.valueOf(categoryId))) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> integers = this.selectStockCategoryAndChildrenById(categoryId).getData();
        if (CollectionUtils.isNotEmpty(integers)) {
            int i = stockCategoryMapper.deleteByPrimaryKeyByIdList(integers);
            if (i > 0) {
                return ServerResponse.createBySuccessMessage("删除节点成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("没有此节点");
        }
        return ServerResponse.createByErrorMessage("删除节点失败");
    }



}
