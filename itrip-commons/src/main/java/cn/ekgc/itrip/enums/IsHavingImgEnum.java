package cn.ekgc.itrip.enums;

public enum IsHavingImgEnum {
    IS_HAVING_IMG_NO(0,"无图片"),
    IS_HAVING_IMG_YES(1,"有图片");
    private Integer code;
    private String remark;

    IsHavingImgEnum(Integer code, String remark) {
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
