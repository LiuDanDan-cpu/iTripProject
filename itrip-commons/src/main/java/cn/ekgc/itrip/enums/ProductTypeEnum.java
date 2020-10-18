package cn.ekgc.itrip.enums;

public enum ProductTypeEnum {
    PRODUCT_TYPE_TRIP(0,"旅游产品"),
    PRODUCT_TYPE_HOTEL(1,"酒店产品"),
    PRODUCT_TYPE_PLANE(2,"机票产品");
    private Integer code;
    private String remark;

    ProductTypeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
