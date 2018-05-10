package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.CompanyContactMapper;
import com.truck.dao.CompanyMapper;
import com.truck.pojo.Company;
import com.truck.pojo.CompanyContact;
import com.truck.service.ICompanyContactService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.CompanyContactVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iCompanyContactService")
public class CompanyContactServiceImpl implements ICompanyContactService {

    @Autowired
    private CompanyContactMapper companyContactMapper;
    @Autowired
    private CompanyMapper companyMapper;

    public ServerResponse add(Integer userId, CompanyContact companyContact){
        Company company = companyMapper.selectByUserId(userId);
        if ( company !=null){
            companyContact.setCompanyId(company.getCompanyId());
        }
        int rowCount = companyContactMapper.insertSelective(companyContact);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("ContactId",companyContact.getContactId());
            return ServerResponse.createBySuccess("新建联系人成功",result);
        }
        return ServerResponse.createByErrorMessage("新建联系人失败");
    }

    public ServerResponse<String> del( Integer companyContactId){
        int rowCount = companyContactMapper.deleteByPrimaryKey( companyContactId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除联系人成功");
        }
        return ServerResponse.createByErrorMessage("删除联系人失败");
    }

    public ServerResponse update( CompanyContact companyContact){
        int rowCount = companyContactMapper.updateByPrimaryKeySelective(companyContact);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新联系人成功");
        }
        return ServerResponse.createByErrorMessage("更新联系人失败");
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Company company = companyMapper.selectByUserId(userId);
        List<CompanyContact> companyContactList = companyContactMapper.selectByCompanyId(company.getCompanyId());
        List<CompanyContactVo> companyContactVoList = Lists.newArrayList();
        for(CompanyContact companyContactItem : companyContactList){
            CompanyContactVo companyContactVo = assembleCompanyContactVo(companyContactItem);
            companyContactVoList.add(companyContactVo);
        }
        PageInfo pageInfo = new PageInfo(companyContactList);
        pageInfo.setList(companyContactVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    private CompanyContactVo assembleCompanyContactVo(CompanyContact companyContact){
        CompanyContactVo companyContactVo = new CompanyContactVo();
        companyContactVo.setContactId(companyContact.getContactId());
        companyContactVo.setCompanyId(companyContact.getCompanyId());
        companyContactVo.setContactName(companyContact.getContactName());
        companyContactVo.setContactPosition(companyContact.getContactPosition());
        companyContactVo.setContactEmail(companyContact.getContactEmail());
        companyContactVo.setContactPhone(companyContact.getContactPhone());
        companyContactVo.setCreateTime(DateTimeUtil.dateToStr(companyContact.getCreateTime()));
        companyContactVo.setUpdateTime(DateTimeUtil.dateToStr(companyContact.getUpdateTime()));
        return companyContactVo;
    }
}
