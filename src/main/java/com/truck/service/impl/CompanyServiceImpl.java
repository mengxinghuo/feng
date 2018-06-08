package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.ProductMapper;
import com.truck.dao.CompanyMapper;
import com.truck.dao.UserInfoMapper;
import com.truck.pojo.Product;
import com.truck.pojo.Company;
import com.truck.pojo.UserInfo;
import com.truck.service.ICompanyService;
import com.truck.service.IUserInfoService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.ProductListVo;
import com.truck.vo.CompanyDetailVo;
import com.truck.vo.CompanyListVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("iCompanyService")
public class CompanyServiceImpl implements ICompanyService {


    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IUserInfoService iUserInfoService;
    @Autowired
    private UserInfoMapper userInfoMapper;


    public ServerResponse saveOrUpdateCompany(Integer userId , Company company,String dutyId,String officeAddress,String companyFax,String companyContactor){
        if(company != null)
        {
            if(StringUtils.isNotBlank(company.getCompanySubimg())){
                String[] subImageArray = company.getCompanySubimg().split(",");
                if(subImageArray.length > 0){
                    company.setCompanyFirstimg(subImageArray[0]);
                }
            }

            if(company.getCompanyId() != null){
                if(!StringUtils.isEmpty(dutyId)){
                    UserInfo checkUserInfo = userInfoMapper.selectByDutyIdExcept(userId,dutyId);
                    if(checkUserInfo != null){
                        return ServerResponse.createByErrorMessage("税号已存在，请重新填写!");
                    }
                }
                int rowCount = companyMapper.updateByPrimaryKeySelective(company);
                if(rowCount > 0){
                    UserInfo userInfo = userInfoMapper.selectCountByUserId(userId);
                    if(userInfo == null){
                        iUserInfoService.addUserInfo(userId, company, dutyId, officeAddress, companyFax, companyContactor);
                    }else{
                        iUserInfoService.updateUserInfo(userId, company, dutyId, officeAddress, companyFax, companyContactor);
                    }
                    return ServerResponse.createBySuccess("更新公司成功");
                }
                return ServerResponse.createBySuccess("更新公司失败");
            }else{
                company.setUserId(userId);

                ServerResponse validResponse=this.checkValid(company.getUserId().toString(), Const.SHOPNUM);
                if (!validResponse.isSuccess())
                    return validResponse;

                validResponse=this.checkValid(company.getCompanyName(),Const.SHOPNAME);
                if (!validResponse.isSuccess())
                    return validResponse;

                validResponse=this.checkValid(company.getCompanyEmail(),Const.SHOPEMAIL);
                if (!validResponse.isSuccess())
                    return validResponse;
                if(StringUtils.isEmpty(dutyId) || StringUtils.isEmpty(officeAddress) || StringUtils.isEmpty(companyContactor)){
                    return ServerResponse.createByErrorMessage("请完善信息");
                }
                UserInfo checkUserInfo = userInfoMapper.selectUserInfoByDutyId(dutyId);
                if(checkUserInfo != null){
                    return ServerResponse.createByErrorMessage("税号已存在，请重新填写!");
                }
                int rowCount = companyMapper.insert(company);
                if(rowCount > 0){
                    Map result = Maps.newHashMap();
                    result.put("companyId",company.getCompanyId());
                    company = companyMapper.selectByPrimaryKey(company.getCompanyId());
                    iUserInfoService.addUserInfo(userId, company, dutyId, officeAddress, companyFax, companyContactor);
                    return ServerResponse.createBySuccess("新增公司成功",result);
                }
                return ServerResponse.createBySuccess("新增公司失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新公司参数不正确");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.SHOPNAME.equals(type)){
                int resultCount=companyMapper.checkCompanyName(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("公司名已存在");
            }

            if (Const.SHOPNUM.equals(type)){
                int resultCount=companyMapper.checkCompanyNum(Integer.parseInt(str));
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("已经有一个自己的公司");
            }

            if (Const.SHOPEMAIL.equals(type)){
                int resultCount=companyMapper.checkCompanyEmail(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("公司email已存在");
            }

        }else {
            return ServerResponse.createByErrorMessage("参数错误");

        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    private CompanyDetailVo assembleCompanyDetailVo(Company company){
        CompanyDetailVo companyDetailVo = new CompanyDetailVo();
        companyDetailVo.setUserId(company.getUserId());

        companyDetailVo.setCompanyAddress(company.getCompanyAddress());
        companyDetailVo.setCompanyDesc(company.getCompanyDesc());
        companyDetailVo.setCompanyEmail(company.getCompanyEmail());
        companyDetailVo.setCompanyFirstimg(company.getCompanyFirstimg());
        companyDetailVo.setCompanyHeadimg(company.getCompanyHeadimg());
        companyDetailVo.setCompanyName(company.getCompanyName());
        companyDetailVo.setCompanyPhone(company.getCompanyPhone());
        companyDetailVo.setCompanyAccount(company.getCompanyAccount());
        companyDetailVo.setCompanyTaxCard(company.getCompanyTaxCard());
        companyDetailVo.setCompanySppkp(company.getCompanySppkp());
        companyDetailVo.setCompanyLicence(company.getCompanyLicence());

        UserInfo userInfo = userInfoMapper.selectCountByUserId(company.getUserId());
        if(userInfo !=null){
            companyDetailVo.setDutyId(userInfo.getDutyId());
            companyDetailVo.setOfficeAddress(userInfo.getOfficeAddress());
            companyDetailVo.setCompanyFax(userInfo.getCompanyFax());
            companyDetailVo.setCompanyContactor(userInfo.getCompanyContactor());
        }
        List<String> subimgList = Lists.newArrayList();
        if(StringUtils.isNotBlank(company.getCompanySubimg())){
            String[] subImageArray = company.getCompanySubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }

        if(StringUtils.isNotBlank(company.getCompanySubimg())){
            String[] subImageArray = company.getCompanySubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }

        companyDetailVo.setCompanySubimg(subimgList);
        companyDetailVo.setCompanyId(company.getCompanyId());

        companyDetailVo.setCreateTime(DateTimeUtil.dateToStr(company.getCreateTime()));
        companyDetailVo.setUpdateTime(DateTimeUtil.dateToStr(company.getUpdateTime()));
        return companyDetailVo;
    }



    public ServerResponse<PageInfo> getCompanyList(int pageNum, int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Company> companyList = companyMapper.selectList();

        List<CompanyListVo> companyListVoList = Lists.newArrayList();
        for(Company companyItem : companyList){
            CompanyListVo companyListVo = assembleCompanyListVo(companyItem);
            companyListVoList.add(companyListVo);
        }
        PageInfo pageResult = new PageInfo(companyList);
        pageResult.setList(companyListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<PageInfo> getCompanyList(Integer userId, int pageNum, int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        Company company = companyMapper.selectByUserId(userId);
        List<CompanyListVo> companyListVoList = Lists.newArrayList();
        CompanyListVo companyListVo = assembleCompanyListVo(company);
        companyListVoList.add(companyListVo);
        PageInfo pageResult = new PageInfo(companyListVoList);
        pageResult.setList(companyListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private CompanyListVo assembleCompanyListVo(Company company){
        CompanyListVo companyListVo = new CompanyListVo();
        companyListVo.setCompanyId(company.getCompanyId());
        companyListVo.setUserId(company.getUserId());
        companyListVo.setCompanyDesc(company.getCompanyDesc());
        companyListVo.setCompanyFirstimg(company.getCompanyFirstimg());
        companyListVo.setCompanyHeadimg(company.getCompanyHeadimg());
        List<String> subimgList = Lists.newArrayList();

        if(StringUtils.isNotBlank(company.getCompanySubimg())){
            String[] subImageArray = company.getCompanySubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }
        companyListVo.setCompanySubimg(subimgList);
        companyListVo.setCompanyName(company.getCompanyName());
        return companyListVo;
    }


    private ProductListVo assembleProductListVo(Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setProductId(product.getProductId());
        productListVo.setAdminId(product.getAdminId());
        productListVo.setProductStatus(product.getProductStatus());
        productListVo.setProductSubtitle(product.getProductSubtitle());
        productListVo.setProductPrice(product.getProductPrice());
        productListVo.setProductFirstimg(product.getProductFirstimg());
        productListVo.setCatagoryVoList(product.getCategoryVoList());
        productListVo.setProductTitle(product.getProductTitle());
        productListVo.setHigh(product.getHigh());
        productListVo.setWide(product.getWide());
        productListVo.setLong(product.getLong());
        productListVo.setProductWeight(product.getProductWeight());
        productListVo.setCreatetime(DateTimeUtil.dateToStr(product.getCreatetime()));
        productListVo.setEndtime(DateTimeUtil.dateToStr(product.getEndtime()));
        productListVo.setProductStatus(product.getProductStatus());
        productListVo.setProductStock(product.getProductStock());
        productListVo.setProductPromotion(product.getProductPromotion());
        productListVo.setIdCode(product.getIdCode());
        productListVo.setEngineType(product.getEngineType());
        productListVo.setPartsNo(product.getPartsNo());
        productListVo.setSystemNo(product.getSystemNo());
        productListVo.setPartsSerialNo(product.getPartsSerialNo());

        productListVo.setPartsNoTwo(product.getPartsNoTwo());
        productListVo.setPartsNoThree(product.getPartsNoThree());
        productListVo.setPartsNoFour(product.getPartsNoFour());
        productListVo.setUnit(product.getUnit());
        productListVo.setDescEnglish(product.getDescEnglish());
        productListVo.setProductBrand(product.getProductBrand());
        return productListVo;
    }

    public ServerResponse<PageInfo> searchCompany(String companyName, Integer companyId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(companyName)){
            companyName = new StringBuilder().append("%").append(companyName).append("%").toString();
        }
        List<Company> companyList = companyMapper.selectByNameAndCompanyId(companyName,companyId);
        List<CompanyListVo> companyListVoList = Lists.newArrayList();
        for(Company companyItem : companyList){
            CompanyListVo companyListVo = assembleCompanyListVo(companyItem);
            companyListVoList.add(companyListVo);
        }
        PageInfo pageResult = new PageInfo(companyList);
        pageResult.setList(companyListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<PageInfo> searchCompany(Integer userId , String companyName, Integer companyId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(companyName)){
            companyName = new StringBuilder().append("%").append(companyName).append("%").toString();
        }
        List<Company> companyList = companyMapper.selectByNameAndCompanyIdUserId( userId ,companyName,companyId);
        List<CompanyListVo> companyListVoList = Lists.newArrayList();
        for(Company companyItem : companyList){
            CompanyListVo companyListVo = assembleCompanyListVo(companyItem);
            companyListVoList.add(companyListVo);
        }
        PageInfo pageResult = new PageInfo(companyList);
        pageResult.setList(companyListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }


    public ServerResponse<CompanyDetailVo> getCompanyDetail(Integer companyId){
        if(companyId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Company company = companyMapper.selectByPrimaryKey(companyId);
        if(company == null){
            return ServerResponse.createByErrorMessage("公司已删除");
        }
        CompanyDetailVo companyDetailVo = assembleCompanyDetailVo(company);
        return ServerResponse.createBySuccess(companyDetailVo);
    }

    public ServerResponse<CompanyDetailVo> getCompanyDetailSelf(Integer userId){
        if(userId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Company company = companyMapper.selectByUserId(userId);
        if(company == null){
            return ServerResponse.createByErrorMessage("公司已删除");
        }
        CompanyDetailVo companyDetailVo = assembleCompanyDetailVo(company);
        return ServerResponse.createBySuccess(companyDetailVo);
    }

    //根据UserId删除产品
    public ServerResponse<String> deleteByUserId(Integer userId) {

        int i = companyMapper.deleteByUserId(userId);

        if (i > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    }


























