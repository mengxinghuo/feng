package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Product;
import com.truck.service.IAdminService;
import com.truck.service.ProductService;
import com.truck.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by master
 */
@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping(value = "selectById.do")
    @ResponseBody
    //查询产品详情
    public ServerResponse<ProductDetailVo> selectProductById(HttpSession session, Integer productId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return productService.manageProductDetail(productId);
    }

    @RequestMapping(value = "selectList.do")
    @ResponseBody
    //查询所有产品
    public ServerResponse<List<Product>> selectProductList(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return productService.selectProductList();
        } else {
            return productService.selectProductList(admin.getAdminId());
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    //根据关键字或者分类ID查询产品
    public ServerResponse<PageInfo> search(@RequestParam(value = "productKeyword", required = false) String productKeyword,
                                           @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                           @RequestParam(value = "categoryKeyword", required = false) String categoryKeyword,
                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "order", defaultValue = "Product_Price") String order,
                                           @RequestParam(value = "by", defaultValue = "desc") String by) {
        return productService.getProductByKeywordCategory(productKeyword, categoryId,categoryKeyword, pageNum, pageSize, order, by);
    }

    @RequestMapping("deleteById.do")
    @ResponseBody
    //删除
    public ServerResponse<String> deleteProductById(HttpSession session, Integer productId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return productService.deleteProductByID(productId);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }


    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @ResponseBody
    //新增产品或者更新产品
    public ServerResponse productSave(HttpSession session, Product product) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return productService.saveOrUpdateProduct(admin.getAdminId(), product);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }


    @RequestMapping("deleteFromServer.do")
    @ResponseBody
    //删除服务器图片
    public ServerResponse deleteProductById(HttpSession session, String Url) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        //TODO productService
        return null;
    }

    //上下架
    @RequestMapping("updown.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer status,
                                        @RequestParam(value = "productId")Integer productId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return productService.setSaleStatus(status,productId);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }
}
