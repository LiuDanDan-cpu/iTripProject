package cn.ekgc.itrip.enums;

public enum HotEnum {
    HOT_YES(1,"热门"),
    HOT_NO(0,"非热门");
    private Integer code;
    private String remark;

    HotEnum(Integer code, String remark) {
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
