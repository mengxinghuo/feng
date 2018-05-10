package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.IAdminService;
import com.truck.service.IStockCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/manage/stockCategory")
public class StockCategoryManageController {

    @Autowired
    private IStockCategoryService iStockCategoryService;
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parentId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iStockCategoryService.addStockCategory(admin.getAdminId(),categoryName,parentId);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("setName.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName){
       Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            //更新categoryName
            return iStockCategoryService.updateStockCategoryName(categoryId,categoryName);
    }

    @RequestMapping("get.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
       Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            //查询子节点的category信息,并且不递归,保持平级
            return iStockCategoryService.getChildrenParallelStockCategory(categoryId);
    }

    /**
     * 得到当前节点的id和递归子节点的id
     * @param session
     * @param categoryId
     * @return
     */
    @RequestMapping("getDeep.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        //查询当前节点的id和递归子节点的id
        return iStockCategoryService.selectStockCategoryAndChildrenById(categoryId);
    }

    /**
     * 得到当前节点的id和递归子节点的对象
     * @param session
     * @param categoryId
     * @return
     */
    @RequestMapping("getDeepCategory.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategoryObject(HttpSession session, @RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        //查询当前节点的id和递归子节点的id
        return iStockCategoryService.selectStockCategoryObjectAndChildrenById(categoryId);
    }

    @RequestMapping("del.do")
    @ResponseBody
    //删除分类
    public ServerResponse addCategory(HttpSession session, Integer categoryId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return iStockCategoryService.deleteById(categoryId);
    }

}
