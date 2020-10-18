package cn.ekgc.itrip.enums;

public enum CtiyTyprEnum {
    CTIY_TYPR_YES(1,"国内"),
    CTIY_TYPR_NO(0,"国外");
    private Integer code;
    private String remark;

    CtiyTyprEnum(Integer code, String remark) {
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
