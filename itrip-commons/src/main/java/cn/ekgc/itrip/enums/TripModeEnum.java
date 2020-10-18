package cn.ekgc.itrip.enums;

public enum  TripModeEnum {
    TRIP_MODE_SELF_DRIVING(0,"自驾"),
    ;
    private Integer code;
    private String remark;

    private TripModeEnum(Integer code, String remark) {
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
