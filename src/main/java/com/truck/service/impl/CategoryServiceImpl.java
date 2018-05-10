package com.truck.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.CategoryMapper;
import com.truck.dao.ProductMapper;
import com.truck.pojo.Category;
import com.truck.service.CategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by geely
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;


    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setProduct_Status(true);//这个分类是可用的

        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    public ServerResponse addCategory(Integer adminId, String categoryName, Integer parentId) {
        if (adminId == null || parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setAdminId(adminId);
        category.setProduct_Status(true);//这个分类是可用的

        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer adminId, Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentIdAdminId(adminId, categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }


    /**
     * 递归查询本节点的id及孩子节点的id
     *
     * @param categoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    @Override
    public List<Integer> selectCategoryAndChildrenByIdList(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return categoryIdList;
    }

    private List<Category> selectParent() {
        return categoryMapper.selectParent();
    }

    //Tree状递归
    public List<Category> findtree(Integer id) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(id);
        for (Category category : categoryList) {
            category.setCategoryList(findtree(category.getId()));
        }
        return categoryList;

    }

    //返回树状关系图
    public ServerResponse<List<Category>> selectCategoryAll() {
        List<Category> categoryListParent = categoryMapper.selectParent();
        for (Category item : categoryListParent) {
            item.setCategoryList(this.findtree(item.getId()));
        }
        return ServerResponse.createBySuccess(categoryListParent);
    }

    public ServerResponse deleteById(Integer categoryId) {
        if (StringUtils.isBlank(String.valueOf(categoryId))) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> integers = this.selectCategoryAndChildrenById(categoryId).getData();
        if (CollectionUtils.isNotEmpty(integers)) {
            int i = categoryMapper.deleteByPrimaryKeyByIdList(integers);
            if (i > 0) {
                return ServerResponse.createBySuccessMessage("删除节点成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("没有此节点");
        }
        return ServerResponse.createByErrorMessage("删除节点失败");
    }


    //递归算法,算出子节点
    public Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }


}
