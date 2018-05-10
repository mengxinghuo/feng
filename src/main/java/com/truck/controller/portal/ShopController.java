package com.truck.controller.portal;


import com.truck.common.ServerResponse;

import com.truck.service.IShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by geely
 */

@Controller
@RequestMapping("/shop/")
public class ShopController {


    @Autowired
    private IShopService iShopService;



    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(Integer shopId){
            return iShopService.getShopDetail(shopId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList( @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
            return iShopService.getShopReviewedList(pageNum,pageSize);
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse shopSearch( @RequestParam(value = "shopName",required = false)String shopName,
                                      @RequestParam(value = "shopId",required = false)Integer shopId,
                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
            return iShopService.searchShop(shopName,shopId,pageNum,pageSize);
    }
}
