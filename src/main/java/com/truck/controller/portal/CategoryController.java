package com.truck.controller.portal;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.CategoryService;
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
@RequestMapping("/category/")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @RequestMapping("get_category.do")
    @ResponseBody
    //查询分类
    public ServerResponse getChildrenParallelCategory( @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        //查询子节点的category信息,并且不递归,保持平级
        return categoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_Allcategory.do")
    @ResponseBody
    //查询分类
    public ServerResponse getAllChildrenParallelCategory() {
        return categoryService.selectCategoryAll();
    }


    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory( @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        return categoryService.selectCategoryAndChildrenById(categoryId);
    }



}
