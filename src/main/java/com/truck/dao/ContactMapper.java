package com.truck.dao;

import com.truck.pojo.Contact;
import com.truck.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactMapper {
    int deleteByPrimaryKey(Integer contactId);

    int insert(Contact record);

    int insertSelective(Contact record);

    Contact selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(Contact record);

    int updateByPrimaryKey(Contact record);

    List<Contact> selectByShopId(@Param("shopId") Integer shopId);
}