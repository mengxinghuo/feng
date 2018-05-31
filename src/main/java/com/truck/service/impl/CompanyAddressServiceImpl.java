package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.CompanyAddressMapper;
import com.truck.dao.CompanyMapper;
import com.truck.pojo.Company;
import com.truck.pojo.CompanyAddress;
import com.truck.pojo.Shop;
import com.truck.service.ICompanyAddressService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.CompanyAddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iCompanyAddressService")
public class CompanyAddressServiceImpl implements ICompanyAddressService {

    @Autowired
    private CompanyAddressMapper companyAddressMapper;
    @Autowired
    private CompanyMapper companyMapper;

    public ServerResponse add(Integer userId, CompanyAddress companyAddress){
        Company company = companyMapper.selectByUserId(userId);
        if (company !=null){
            companyAddress.setCompanyId(company.getCompanyId());
        }
        int rowCount = companyAddressMapper.insertSelective(companyAddress);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("AddressId",companyAddress.getAddressId());
            return ServerResponse.createBySuccess("新建仓库成功",result);
        }
        return ServerResponse.createByErrorMessage("新建仓库失败");
    }

    public ServerResponse<String> del( Integer companyAddressId){
        int rowCount = companyAddressMapper.deleteByPrimaryKey( companyAddressId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除仓库成功");
        }
        return ServerResponse.createByErrorMessage("删除仓库失败");
    }

    public ServerResponse update( CompanyAddress companyAddress){
        int rowCount = companyAddressMapper.updateByPrimaryKeySelective(companyAddress);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新仓库成功");
        }
        return ServerResponse.createByErrorMessage("更新仓库失败");
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Company company = companyMapper.selectByUserId(userId);
        List<CompanyAddress> companyAddressList = Lists.newArrayList();
        if (company != null) {
             companyAddressList = companyAddressMapper.selectByCompanyId(company.getCompanyId());
        }
        List<CompanyAddressVo> companyAddressVoList = Lists.newArrayList();
        for(CompanyAddress companyAddressItem : companyAddressList){
            CompanyAddressVo companyAddressVo = assembleCompanyAddressVo(companyAddressItem);
            companyAddressVoList.add(companyAddressVo);
        }
        PageInfo pageInfo = new PageInfo(companyAddressList);
        pageInfo.setList(companyAddressVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }

    private CompanyAddressVo assembleCompanyAddressVo(CompanyAddress companyAddress){
        CompanyAddressVo companyAddressVo = new CompanyAddressVo();
        companyAddressVo.setAddressId(companyAddress.getAddressId());
        companyAddressVo.setCompanyId(companyAddress.getCompanyId());
        companyAddressVo.setAddressName(companyAddress.getAddressName());
        companyAddressVo.setAddressDesc(companyAddress.getAddressDesc());
        companyAddressVo.setAddressEmail(companyAddress.getAddressEmail());
        companyAddressVo.setAddressPhone(companyAddress.getAddressPhone());
        companyAddressVo.setCreateTime(DateTimeUtil.dateToStr(companyAddress.getCreateTime()));
        companyAddressVo.setUpdateTime(DateTimeUtil.dateToStr(companyAddress.getUpdateTime()));
        return companyAddressVo;
    }
}
