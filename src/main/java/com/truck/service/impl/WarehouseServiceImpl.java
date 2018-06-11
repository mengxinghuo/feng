package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ShopMapper;
import com.truck.dao.StockCategoryMapper;
import com.truck.dao.StockMapper;
import com.truck.dao.WarehouseMapper;
import com.truck.pojo.Shop;
import com.truck.pojo.Stock;
import com.truck.pojo.StockCategory;
import com.truck.pojo.Warehouse;
import com.truck.service.IStockService;
import com.truck.service.IWarehouseService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.StockVo;
import com.truck.vo.WarehouseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iWarehouseService")
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockCategoryMapper stockCategoryMapper;
    @Autowired
    private IStockService stockService;

    public ServerResponse add(Integer adminId,Warehouse warehouse){
         Shop shop = shopMapper.selectByAdminId(adminId);
         if (shop !=null){
             warehouse.setShopId(shop.getShopId());
         }
        int rowCount = warehouseMapper.insertSelective(warehouse);
        if(rowCount > 0){
            StockCategory stockCategory = new StockCategory();
            stockCategory.setParentId(0);
            stockCategory.setAdminId(adminId);
            stockCategory.setName(warehouse.getWarehouseName());
             stockCategoryMapper.insertSelective(stockCategory);
            Map result = Maps.newHashMap();
            result.put("warehouseId",warehouse.getWarehouseId());
            return ServerResponse.createBySuccess("新建仓库成功",result);
        }
        return ServerResponse.createByErrorMessage("新建仓库失败");
    }

    public ServerResponse<String> del(Integer warehouseId){
        int rowCount = warehouseMapper.deleteByPrimaryKey( warehouseId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除仓库成功");
        }
        return ServerResponse.createByErrorMessage("删除仓库失败");
    }

    public ServerResponse update(Warehouse warehouse){
        int rowCount = warehouseMapper.updateByPrimaryKeySelective(warehouse);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新仓库成功");
        }
        return ServerResponse.createByErrorMessage("更新仓库失败");
    }

    public ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Shop shop = shopMapper.selectByAdminId(adminId);
        List<Warehouse> warehouseList = Lists.newArrayList();
        if (shop != null) {
            warehouseList = warehouseMapper.selectByShopId(shop.getShopId());
        }
        List<WarehouseVo> warehouseVoList = Lists.newArrayList();
        for(Warehouse warehouseItem : warehouseList){
            WarehouseVo warehouseVo = assembleWarehouseVo(warehouseItem,null);
            warehouseVoList.add(warehouseVo);
        }
        PageInfo pageInfo = new PageInfo(warehouseList);
        pageInfo.setList(warehouseVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    public WarehouseVo assembleWarehouseVo(Warehouse warehouse,Integer productId){
        WarehouseVo warehouseVo = new WarehouseVo();
        warehouseVo.setWarehouseId(warehouse.getWarehouseId());
        warehouseVo.setShopId(warehouse.getShopId());
        warehouseVo.setWarehouseName(warehouse.getWarehouseName());
        warehouseVo.setWarehouseDesc(warehouse.getWarehouseDesc());
        warehouseVo.setWarehouseEmail(warehouse.getWarehouseEmail());
        warehouseVo.setWarehousePhone(warehouse.getWarehousePhone());
        warehouseVo.setCreateTime(DateTimeUtil.dateToStr(warehouse.getCreateTime()));
        warehouseVo.setUpdateTime(DateTimeUtil.dateToStr(warehouse.getUpdateTime()));
        StockCategory stockCategory = stockCategoryMapper.selectByName(warehouseVo.getWarehouseName());
        if (stockCategory != null) {
            warehouseVo.setStockCategoryId(stockCategory.getId());
        }
        Shop shop = shopMapper.selectByPrimaryKey(warehouse.getShopId());
        if (shop != null) {
        List<Stock> stockList = stockMapper.selectListByProductIdWarehouseId(shop.getAdminId(),productId,warehouse.getWarehouseId(),null);
        if(stockList != null){
            List<StockVo> stockVoList = Lists.newArrayList();
            for (Stock stock : stockList) {
                StockVo stockVo = stockService.assembleStockVo(stock);
                stockVoList.add(stockVo);
            }
            warehouseVo.setStockVoList(stockVoList);
        }
        }
        return warehouseVo;
    }
}
