package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.ContactMapper;
import com.truck.dao.ShopMapper;
import com.truck.pojo.Contact;
import com.truck.pojo.Shop;
import com.truck.service.IContactService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.ContactVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iContactService")
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ShopMapper shopMapper;

    public ServerResponse add(Integer adminId ,Contact contact){
        Shop shop = shopMapper.selectByAdminId(adminId);
        if (shop !=null){
            contact.setShopId(shop.getShopId());
        }
        int rowCount = contactMapper.insertSelective(contact);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("contactId",contact.getContactId());
            return ServerResponse.createBySuccess("新建联系人成功",result);
        }
        return ServerResponse.createByErrorMessage("新建联系人失败");
    }

    public ServerResponse<String> del(Integer contactId){
        int rowCount = contactMapper.deleteByPrimaryKey( contactId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除联系人成功");
        }
        return ServerResponse.createByErrorMessage("删除联系人失败");
    }

    public ServerResponse update(Contact contact){
        int rowCount = contactMapper.updateByPrimaryKeySelective(contact);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新联系人成功");
        }
        return ServerResponse.createByErrorMessage("更新联系人失败");
    }

    public ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Shop shop = shopMapper.selectByAdminId(adminId);
        List<Contact> contactList = Lists.newArrayList();
        if (shop != null) {
             contactList = contactMapper.selectByShopId(shop.getShopId());
        }
        List<ContactVo> contactVoList = Lists.newArrayList();
        for(Contact contactItem : contactList){
            ContactVo contactVo = assembleContactVo(contactItem);
            contactVoList.add(contactVo);
        }
        PageInfo pageInfo = new PageInfo(contactList);
        pageInfo.setList(contactVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    public ContactVo assembleContactVo(Contact contact){
        ContactVo contactVo = new ContactVo();
        contactVo.setContactId(contact.getContactId());
        contactVo.setShopId(contact.getShopId());
        contactVo.setContactName(contact.getContactName());
        contactVo.setContactPosition(contact.getContactPosition());
        contactVo.setContactEmail(contact.getContactEmail());
        contactVo.setContactPhone(contact.getContactPhone());
        contactVo.setCreateTime(DateTimeUtil.dateToStr(contact.getCreateTime()));
        contactVo.setUpdateTime(DateTimeUtil.dateToStr(contact.getUpdateTime()));
        return contactVo;
    }
}
