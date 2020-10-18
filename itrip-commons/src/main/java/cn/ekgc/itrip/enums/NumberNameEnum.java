package cn.ekgc.itrip.enums;

/**
 * <b>订单信息枚举类</b>
 */
public enum  NumberNameEnum {
    NUMBER_NAME_ZERO(0,"旅游"),
    NUMBER_NAME_ONE(1,"酒店"),
    NUMBER_NAME_TWO(2,"机票"),
    ;
    private Integer code;
    private String remark;
    NumberNameEnum(Integer code, String remark) {
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
