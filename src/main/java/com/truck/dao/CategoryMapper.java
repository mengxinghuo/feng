package com.truck.dao;

import com.truck.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectByParentId(Integer id);

    List<Category> selectByCategoryKeyword(String categoryKeyword);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectCategoryChildrenByParentId(Integer parentId);

    List<Category> selectCategoryChildrenByParentIdAll();

    int deleteByPrimaryKeyById(int parent_id);


    int deleteByPrimaryKeyByIdList(@Param("integers") List<Integer> integers);

    List<Category> selectParent();

    List<Category> selectCategoryChildrenByParentIdAdminId(@Param("adminId")Integer adminId, @Param("parentId")Integer parentId);


}