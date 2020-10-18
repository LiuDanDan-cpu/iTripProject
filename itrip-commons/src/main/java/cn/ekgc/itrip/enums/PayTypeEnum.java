package cn.ekgc.itrip.enums;

public enum  PayTypeEnum {
    PAY_TYPE_ON_LINE(1,"在线付"),
    PAY_TYPE_OFFLINE(2,"到店付"),
    PAY_TYPE_ENUM(3,"不限");
    private Integer code;
    private String remark;

    PayTypeEnum(Integer code, String remark) {
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
