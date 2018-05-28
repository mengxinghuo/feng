package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.ProductMapper;
import com.truck.dao.StockAlterationMapper;
import com.truck.dao.StockMapper;
import com.truck.pojo.Product;
import com.truck.pojo.Stock;
import com.truck.pojo.StockAlteration;
import com.truck.service.IStockAlterationService;
import com.truck.service.IStockService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.StockAlterationVo;
import com.truck.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service("iStockAlterationService")
public class StockAlterationServiceImpl implements IStockAlterationService {

    @Autowired
    private StockAlterationMapper stockAlterationMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private IStockService iStockService;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有变动记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse stockAlterationList(Integer adminId,int pageNum, int pageSize, Integer status){
        PageHelper.startPage(pageNum, pageSize);
        List<StockAlteration> stockAlterationList = stockAlterationMapper.selectAllStockAlteration(adminId,status);
        if(stockAlterationList.size() > 0){
            List<StockAlterationVo> stockAlterationVoList = Lists.newArrayList();
            for(StockAlteration stockAlterationItem : stockAlterationList){
                StockAlterationVo stockAlterationVo = this.assembleStockAlterationVo(stockAlterationItem);
                stockAlterationVoList.add(stockAlterationVo);
            }
            PageInfo pageInfo = new PageInfo(stockAlterationList);
            pageInfo.setList(stockAlterationVoList);

            Map map = Maps.newHashMap();
            Integer totalNum = stockAlterationMapper.selectNumAlterationByAdminId(adminId,status);
            map.put("totalNum",totalNum);
            map.put("stockAlterationVoList",pageInfo);
            return ServerResponse.createBySuccess(map);
        }else{
            return ServerResponse.createByErrorMessage("当前没有记录");
        }
    }

    public ServerResponse getListByProductId(Integer adminId,Integer productId, Integer warehouseId, Integer status,String idCode,String searchDate,String beginDate,String endDate,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<StockAlterationVo> stockAlterationVoList = Lists.newArrayList();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(idCode)){
            List<Product> products = productMapper.selectByAdminIdIdCode(adminId,idCode);
            if (products.size()>0){
                productId = products.get(0).getProductId();
            }else{
                return ServerResponse.createBySuccess(stockAlterationVoList);
            }
        }

            List<StockAlteration> stockAlterationList = stockAlterationMapper.selectStockAlterationByProductIdAdminIdWarehouseId(adminId,productId,warehouseId,status,searchDate,beginDate,endDate);
            if(stockAlterationList.size() > 0){
                for(StockAlteration stockAlterationItem : stockAlterationList){
                    StockAlterationVo stockAlterationVo = this.assembleStockAlterationVo(stockAlterationItem);
                    stockAlterationVoList.add(stockAlterationVo);
                }
            }

        PageInfo pageInfo = new PageInfo(stockAlterationList);
        pageInfo.setList(stockAlterationVoList);

        Map map = Maps.newHashMap();
        Integer totalNum = stockAlterationMapper.selectNumAlterationByProductIdAdminIdWarehouseId(adminId,productId,warehouseId,status,searchDate,beginDate,endDate);
        map.put("totalNum",totalNum);
        map.put("stockAlterationVoList",pageInfo);
        return ServerResponse.createBySuccess(map);
    }


    public StockAlterationVo assembleStockAlterationVo(StockAlteration stockAlteration){
        StockAlterationVo stockAlterationVo = new StockAlterationVo();
        stockAlterationVo.setAlterationId(stockAlteration.getAlterationId());
        stockAlterationVo.setStockId(stockAlteration.getStockId());
        stockAlterationVo.setAlterationNum(stockAlteration.getAlterationNum());
        stockAlterationVo.setAlterationStatus(stockAlteration.getAlterationStatus());
        stockAlterationVo.setStatusDesc(Const.StockAlterationStatusEnum.codeOf(stockAlteration.getAlterationStatus()).getValue());
        stockAlterationVo.setAlterationReason(stockAlteration.getAlterationReason());
        stockAlterationVo.setReasonDesc(Const.StockAlterationReasonEnum.codeOf(stockAlteration.getAlterationReason()).getValue());
        stockAlterationVo.setAlterationProductPrice(stockAlteration.getAlterationProductPrice());

        Stock stock = stockMapper.selectByPrimaryKey(stockAlteration.getStockId());
        if (stock != null) {
            StockVo stockVo = iStockService.assembleStockVo(stock);
            stockAlterationVo.setStockVo(stockVo);
        }

        if(!StringUtils.isEmpty(stockAlteration.getOrderDetailId())){
            stockAlterationVo.setOrderDetailId(stockAlteration.getOrderDetailId());
        }
        stockAlterationVo.setCreateTime(DateTimeUtil.dateToStr(stockAlteration.getCreateTime()));
        stockAlterationVo.setUpdateTime(DateTimeUtil.dateToStr(stockAlteration.getUpdateTime()));
        return stockAlterationVo;
    }
}
