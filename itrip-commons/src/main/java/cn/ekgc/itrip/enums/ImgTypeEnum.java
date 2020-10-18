package cn.ekgc.itrip.enums;

public enum  ImgTypeEnum {
    IMG_TYPE_HOTEL("0","酒店图片"),
    IMG_TYPE_EOOM("1","房间图片"),
    IMG_TYPE_COMMENT("2","评论图片");
    private String code;
    private String remark;

    ImgTypeEnum() {
    }

    ImgTypeEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
