package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.CategoryService;
import com.truck.service.IAdminService;
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
@RequestMapping("/manage/category/")
public class CategoryManageController {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private IAdminService iAdminService;

    @RequestMapping("add_category.do")
    @ResponseBody
    //新增分类
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return categoryService.addCategory(admin.getAdminId(), categoryName, parentId);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("delete_category.do")
    @ResponseBody
    //删除分类
    public ServerResponse addCategory(HttpSession session, Integer Id) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return categoryService.deleteById(Id);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    //更新分类
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return categoryService.updateCategoryName(categoryId, categoryName);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }


    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        //查询子节点的category信息,并且不递归,保持平级
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return categoryService.getChildrenParallelCategory(categoryId);
        } else {
            return categoryService.getChildrenParallelCategory(admin.getAdminId(), categoryId);
        }

    }

    @RequestMapping("get_Allcategory.do")
    @ResponseBody
    //查询所有分类,从根节点开始
    public ServerResponse getAllChildrenParallelCategory(HttpSession session) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return categoryService.selectCategoryAll();
    }


    @RequestMapping("get_deep_category.do")
    @ResponseBody
    //递归查询本节点的id及孩子节点的id
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return categoryService.selectCategoryAndChildrenById(categoryId);
    }



}
