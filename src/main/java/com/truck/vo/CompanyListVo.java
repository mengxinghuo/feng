package com.truck.vo;

import java.util.List;

/**
 * Created by geely
 */
public class CompanyListVo {
    private Integer companyId;

    private Integer userId;

    private String companyName;

    private String companyDesc;

    private String companyHeadimg;

    private String companyFirstimg;

    private List<String> companySubimg;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyHeadimg() {
        return companyHeadimg;
    }

    public void setCompanyHeadimg(String companyHeadimg) {
        this.companyHeadimg = companyHeadimg;
    }

    public String getCompanyFirstimg() {
        return companyFirstimg;
    }

    public void setCompanyFirstimg(String companyFirstimg) {
        this.companyFirstimg = companyFirstimg;
    }

    public List<String> getCompanySubimg() {
        return companySubimg;
    }

    public void setCompanySubimg(List<String> companySubimg) {
        this.companySubimg = companySubimg;
    }
}
