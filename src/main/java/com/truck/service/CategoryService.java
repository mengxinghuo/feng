package com.truck.service;


import com.truck.common.ServerResponse;
import com.truck.pojo.Category;

import java.util.List;
import java.util.Set;

/**
 * Created by geely
 */
public interface CategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse addCategory(Integer adminId, String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

    List<Integer> selectCategoryAndChildrenByIdList(Integer categoryId);

    ServerResponse<List<Category>> selectCategoryAll();

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer adminId, Integer categoryId);


    Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId);

    ServerResponse deleteById(Integer categoryId);

}
