package com.truck.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String CURRENT_ADMIN = "currentADMIN";
    public static final String ADMINNAME = "adminname";
    public static final String COMPANYNAME = "companyname";
    public static final String SHOPNAME = "shopname";
    public static final String SHOPEMAIL = "shopemail";
    public static final String SHOPNUM = "adminId";
    public static final String DEVICE_SYSTEM_USER_PHONE = "88888888";


    public interface Role {
        int ROLE_COSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//超级管理员
        int ROLE_VIPUSER = 2;//vip用户
    }

    public interface AdminRole {
        int ADMINROLE_ADMIN = 0;//管理员
        int ADMINROLE_SUPERADMIN = 1; //超级管理员
    }

    public interface Cart {
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }


    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("Product_Price,desc", "Product_Price", "Product_Price,asc");
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum ProductStockStatusEnum {
        OUT_LIMIT(1, "正常"),
        IN_LIMIT(0, "警戒");
        private String value;
        private int code;

        ProductStockStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static ProductStockStatusEnum codeOf(int code) {
            for (ProductStockStatusEnum productStockStatusEnum : values()) {
                if (productStockStatusEnum.getCode() == code) {
                    return productStockStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum ShopStatusEnum {
        REVIEW_SUCCESS(1, "审核通过"),
        REVIEW_FAILED(2, "审核不通过"),
        REVIEWING(0, "审核中");
        private String value;
        private int code;

        ShopStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


    public enum OrderStatusEnum {
        CANCELED(0, "已取消"),
        NO_PAY(10, "未支付"),
        PAID(20, "已付款"),
        SHIPPING(30,"发货中"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "订单完成"),
        ORDER_CLOSE(60, "订单关闭");


        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum OrderDetailStatusEnum {
        CANCELED(0, "已取消"),
        NO_SHIPPING(20,"未发货"),
        DISTRIBUTION(30,"已配货"),
        SHIPPED(40, "已发货"),
        ORDER_SUCCESS(50, "已接收");


        OrderDetailStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderDetailStatusEnum codeOf(int code) {
            for (OrderDetailStatusEnum orderDetailStatusEnum : values()) {
                if (orderDetailStatusEnum.getCode() == code) {
                    return orderDetailStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum AlterationReasonEnum{
        RECHARGE(0,"账户充值"),
        PLACE_ORDER(1,"下单"),
        BACK_ORDER(2,"退单");

        private String value;
        private int code;
        AlterationReasonEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static AlterationReasonEnum codeOf(int code){
            for(AlterationReasonEnum alterationReasonEnum : values()){
                if(alterationReasonEnum.getCode() == code){
                    return alterationReasonEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum AlterationStatusEnum{
        INCOME(0,"收入"),
        EXPENDITURE(1,"支出");

        private String value;
        private int code;
        AlterationStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static AlterationStatusEnum codeOf(int code){
            for(AlterationStatusEnum alterationStatusEnum : values()){
                if(alterationStatusEnum.getCode() == code){
                    return alterationStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum StockAlterationReasonEnum{
        BUY_PRODUCT(0,"进货"),
        SALE_PRODUCT(1,"发货"),
        BACK_PRODUCT(2,"退货"),
        LOSE_PRODUCT(3,"货物损耗");

        private String value;
        private int code;
        StockAlterationReasonEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static StockAlterationReasonEnum codeOf(int code){
            for(StockAlterationReasonEnum stockAlterationReasonEnum : values()){
                if(stockAlterationReasonEnum.getCode() == code){
                    return stockAlterationReasonEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum StockAlterationStatusEnum{
        INSTOCK(0,"入库"),
        OUTSTOCK(1,"出库");

        private String value;
        private int code;
        StockAlterationStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static StockAlterationStatusEnum codeOf(int code){
            for(StockAlterationStatusEnum stockAlterationStatusEnum : values()){
                if(stockAlterationStatusEnum.getCode() == code){
                    return stockAlterationStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public interface AlipayCallback {
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }


    public enum PayPlatformEnum {
        ALIPAY(1, "支付宝");

        PayPlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum {
        ARRIVE_PAY(0, "货到付款"),
        ONLINE_PAY(1, "在线支付");

        PaymentTypeEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code) {
            for (PaymentTypeEnum paymentTypeEnum : values()) {
                if (paymentTypeEnum.getCode() == code) {
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }
}
