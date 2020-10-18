package cn.ekgc.itrip.enums;

public enum IsOkEnum {
    IS_OK_NO(0,"有待改善"),
    IS_OK_YES(1,"值得推荐");
    private Integer code;
    private String remark;

    IsOkEnum(Integer code, String remark) {
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
