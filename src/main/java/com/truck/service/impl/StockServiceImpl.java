package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.*;
import com.truck.pojo.*;
import com.truck.service.IStockCategoryService;
import com.truck.service.IStockService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.StockVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by geely
 */
@Service("iStockService")
public class StockServiceImpl implements IStockService {


    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StockAlterationMapper stockAlterationMapper;

    @Autowired
    private StockCategoryMapper stockCategoryMapper;

    @Autowired
    private IStockCategoryService iStockCategoryService;

    public ServerResponse saveOrUpdateStock(Integer adminId,Stock stock,String vendor,String buyingContract) {
        if (stock != null) {
            stock.setAdminId(adminId);
            if (stock.getStockId() != null) {
                int rowCount = stockMapper.updateByPrimaryKeySelective(stock);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新库存成功");
                }
                return ServerResponse.createBySuccess("更新库存失败");
            } else {
                if (stock.getStockNum() != null && stock.getProductId() != null) {
                    if(stock.getStockLimitNum() !=null){
                        Integer status = (stock.getStockNum() - stock.getStockLimitNum())>=0?1:0;
                        stock.setStockStatus(status);
                    }else{
                        stock.setStockStatus(Const.ProductStockStatusEnum.OUT_LIMIT.getCode());
                    }
                    Shop shop = shopMapper.selectByAdminId(adminId);
                    if (shop != null) {
                        stock.setShopId(shop.getShopId());
                    }
                    int rowCount = stockMapper.insertSelective(stock);
                    if (rowCount > 0) {
                        StockAlteration stockAlteration = new StockAlteration();
                        stockAlteration.setStockId(stock.getStockId());
                        stockAlteration.setAlterationNum(stock.getStockNum());
                        stockAlteration.setAlterationStatus(Const.StockAlterationStatusEnum.INSTOCK.getCode());
                        stockAlteration.setAlterationReason(Const.StockAlterationReasonEnum.BUY_PRODUCT.getCode());
                        stockAlteration.setAlterationProductPrice(stock.getInStockPrice());
                        stockAlterationMapper.insertSelective(stockAlteration);

                        Product product = productMapper.selectByPrimaryKey(stock.getProductId());
                        product.setProductStock(product.getProductStock() + stock.getStockNum());
                        productMapper.updateByPrimaryKeySelective(product);

                        Map result = Maps.newHashMap();
                        result.put("stockId",stock.getStockId());
                        return ServerResponse.createBySuccess("新增库存成功",result);
                    }
                }
                return ServerResponse.createBySuccess("新增库存失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新库存参数不正确");
    }


    public ServerResponse<String> setStockStatus(Integer stockId, Integer status){
        if(stockId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Stock stock = new Stock();
        stock.setStockId(stockId);
        stock.setStockStatus(status);
        int rowCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改库存销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改库存销售状态失败");
    }

    public ServerResponse<String> reduceStockNum(Integer stockId, Integer reduceNum){
        if(stockId == null || reduceNum == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        if (stock.getStockNum() < reduceNum) {
            return ServerResponse.createByErrorMessage("产品库存不足");
        }
        if ((stock.getStockNum() - reduceNum)< stock.getStockLimitNum()) {
            stock.setStockStatus(Const.ProductStockStatusEnum.IN_LIMIT.getCode());
        }
        stock.setStockNum(stock.getStockNum() - reduceNum);
        int rowCount = stockMapper.updateByPrimaryKeySelective(stock);
        if(rowCount > 0){
            StockAlteration stockAlteration = new StockAlteration();
            stockAlteration.setStockId(stock.getStockId());
            stockAlteration.setAlterationNum(stock.getStockNum());
            stockAlteration.setAlterationStatus(Const.StockAlterationStatusEnum.OUTSTOCK.getCode());
            stockAlteration.setAlterationReason(Const.StockAlterationReasonEnum.LOSE_PRODUCT.getCode());
            stockAlteration.setAlterationProductPrice(stock.getInStockPrice());
            stockAlterationMapper.insertSelective(stockAlteration);

            return ServerResponse.createBySuccess("减少库存数量成功");
        }
        return ServerResponse.createByErrorMessage("减少库存数量失败");
    }

    public ServerResponse<String> addStockNum(Integer stockId, Integer addNum){
        if(stockId == null || addNum == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        stock.setStockNum(stock.getStockNum() + addNum);

        if (stock.getStockNum() != null && stock.getProductId() != null) {
            int rowCount = stockMapper.updateByPrimaryKeySelective(stock);

            if (rowCount > 0) {
                StockAlteration stockAlteration = new StockAlteration();
                stockAlteration.setStockId(stock.getStockId());
                stockAlteration.setAlterationNum(stock.getStockNum());
                stockAlteration.setAlterationStatus(Const.StockAlterationStatusEnum.INSTOCK.getCode());
                stockAlteration.setAlterationReason(Const.StockAlterationReasonEnum.BUY_PRODUCT.getCode());
                stockAlteration.setAlterationProductPrice(stock.getInStockPrice());
                stockAlterationMapper.insertSelective(stockAlteration);

                Product product = productMapper.selectByPrimaryKey(stock.getProductId());
                product.setProductStock(product.getProductStock() + addNum);
                productMapper.updateByPrimaryKeySelective(product);
                return ServerResponse.createBySuccess("增加库存数量成功");
            }
            return ServerResponse.createByErrorMessage("增加库存数量失败");
        }
        return ServerResponse.createByErrorMessage("增加库存数量失败");
    }

    public StockVo assembleStockVo(Stock stock){
        StockVo stockVo = new StockVo();
        stockVo.setAdminId(stock.getAdminId());
        stockVo.setProductId(stock.getProductId());
        stockVo.setShopId(stock.getShopId());
        stockVo.setStockId(stock.getStockId());
        stockVo.setStockLimitNum(stock.getStockLimitNum());
        stockVo.setStockNum(stock.getStockNum());
        stockVo.setStockStatus(stock.getStockStatus());
        stockVo.setWarehouseId(stock.getWarehouseId());
        stockVo.setStockCategoryId(stock.getStockCategoryId());
        stockVo.setInStockPrice(stock.getInStockPrice());

        StockCategory stockCategory = stockCategoryMapper.selectByPrimaryKey(stock.getStockCategoryId());
        if(stockCategory == null){
            stockVo.setParentCategoryId(0);//默认根节点
        }else{
            stockVo.setParentCategoryId(stockCategory.getParentId());
        }
        List<Integer> idList = Lists.newArrayList();
        findDeepParentId(idList,stockVo.getStockCategoryId());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = idList.size() - 1; i >= 0; i--) {
            StockCategory stockCategorys = stockCategoryMapper.selectByPrimaryKey(idList.get(i));
            if (stockCategorys != null) {
                stringBuilder.append(stockCategorys.getName());
            }
        }
        stockVo.setStockName(stringBuilder.toString());
        stockVo.setStockStatusDesc(Const.ProductStockStatusEnum.codeOf(stock.getStockStatus()).getValue());
        stockVo.setType(stock.getType());
        stockVo.setTypeDesc(Const.ProductStockTypeEnum.codeOf(stock.getType()).getValue());
        stockVo.setCreateTime(DateTimeUtil.dateToStr(stock.getCreateTime()));
        stockVo.setUpdateTime(DateTimeUtil.dateToStr(stock.getUpdateTime()));
        return stockVo;
    }

    //递归算法,算出父节点
    private List<Integer> findDeepParentId(List<Integer> idList , Integer stockCategoryId){
        StockCategory stockCategory = stockCategoryMapper.selectByPrimaryKey(stockCategoryId);
        if(stockCategory != null){
            idList.add(stockCategory.getId());
            //查找父节点,递归算法一定要有一个退出的条件
            if(stockCategory.getParentId() >0){
                findDeepParentId(idList,stockCategory.getParentId());
            }
        }
        return idList;
    }

    public ServerResponse selectListByProductIdWarehouseId(Integer adminId,Integer productId,String idCode,Integer warehouseId, Integer stockStatus,int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<StockVo> stockVoList = Lists.newArrayList();
        if (StringUtils.isNotBlank(idCode)){
            List<Product> products = productMapper.selectByAdminIdIdCode(adminId,idCode);
            if (products.size()>0){
                productId = products.get(0).getProductId();
            }else{
                return ServerResponse.createBySuccess(stockVoList);
            }
        }
        List<Stock> stockList = stockMapper.selectListByProductIdWarehouseId(adminId,productId,warehouseId,stockStatus);

        for(Stock stockItem : stockList){
            StockVo stockVo = assembleStockVo(stockItem);
            stockVoList.add(stockVo);
        }
        PageInfo pageResult = new PageInfo(stockList);
        pageResult.setList(stockVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

/*    public ServerResponse<PageInfo> searchStock(String stockName,Integer stockId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(stockName)){
            stockName = new StringBuilder().append("%").append(stockName).append("%").toString();
        }
        List<Stock> stockList = stockMapper.selectByNameAndStockId(stockName,stockId);
        List<StockListVo> stockListVoList = Lists.newArrayList();
        for(Stock stockItem : stockList){
            StockListVo stockListVo = assembleStockListVo(stockItem);
            stockListVoList.add(stockListVo);
        }
        PageInfo pageResult = new PageInfo(stockList);
        pageResult.setList(stockListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }*/


    public ServerResponse<StockVo> getStockDetail(Integer stockId){
        if(stockId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        if(stock == null){
            return ServerResponse.createByErrorMessage("库存已删除");
        }
      /*  if(stock.getStockStatus() != Const.StockStatusEnum.ON_SALE.getCode()){
            return ServerResponse.createByErrorMessage("库存已下架或者删除");
        }*/
        StockVo stockVo = assembleStockVo(stock);
        return ServerResponse.createBySuccess(stockVo);
    }


 /*   public ServerResponse<PageInfo> getStockByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        if(StringUtils.isBlank(keyword) && categoryId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                PageHelper.startPage(pageNum,pageSize);
                List<StockListVo> stockListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(stockListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.StockListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Stock> stockList = stockMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);

        List<StockListVo> stockListVoList = Lists.newArrayList();
        for(Stock stock : stockList){
            StockListVo stockListVo = assembleStockListVo(stock);
            stockListVoList.add(stockListVo);
        }

        PageInfo pageInfo = new PageInfo(stockList);
        pageInfo.setList(stockListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }*/
}
