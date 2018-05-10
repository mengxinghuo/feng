package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Product;
import com.truck.vo.ProductDetailVo;
import com.truck.vo.ProductListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by master on 2018/3/12.
 */
public interface ProductService {

    //根据ID查询产品
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);


    //超级管理员查询所有
    ServerResponse<List<Product>> selectProductList();

    //查询所有
    ServerResponse<List<Product>> selectProductList(int adminId);


    //根据ID删除产品
    ServerResponse<String> deleteProductByID(Integer productId);


    //新增产品
    //更新产品
    ServerResponse saveOrUpdateProduct(Integer adminId,Product product);

    //警戒线
    ServerResponse selectCountMin(int Min);

    ProductListVo assembleProductListVo(Product product);


    //分页查询
//    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String order, String by);

    ServerResponse<PageInfo> getProductByKeywordCategory(String productKeyword, Integer categoryId, String categoryKeyword, int pageNum, int pageSize, String order, String by);

    //上下架
    ServerResponse<String> setSaleStatus(Integer status,Integer productId);

    ServerResponse<List<Product>> selectAllByid(int id);

}
