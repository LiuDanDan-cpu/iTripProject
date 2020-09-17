package cn.ekgc.itrip.enums;

/**
 * <b>用户注册枚举信息</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum  UserTypeEnum {
    USER_TYPE_SELF(0,"自注册用户"),
    USER_TYPE_WECHAT(1,"微信注册"),
    USER_TYPE_QQ(2,"QQ"),
    USER_TYPE_WEIBO(3,"微博");
    private Integer code;
    private String remark;

    UserTypeEnum(Integer code, String remark) {
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
