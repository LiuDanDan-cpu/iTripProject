package cn.ekgc.itrip.enums;

public enum  TradingAreaEnum {
    TRADING_AREA_YES(1,"商圈"),
    TRADING_AREA_NO(0,"非商圈");

    private Integer code;
    private String remark;

   TradingAreaEnum(Integer code, String remark) {
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
