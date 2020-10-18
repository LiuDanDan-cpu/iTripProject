package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>前端传输过来修改接收vo对象</b>
 */
public class LinkUserVO implements Serializable {
    private static final long serialVersionUID = 9033543618830449781L;
//    @ApiModelProperty("[必填] 主键")
    private Long id;
//    @ApiModelProperty("[必填] 常用刚联系人姓名")
    private String linkUserName;
//    @ApiModelProperty("[必填] 证件类型")
    private Integer linkIdCardType;
//    @ApiModelProperty("[必填] 证件号码")
    private String linkIdCard;
//    @ApiModelProperty("[非必填] 联系电话")
    private String linkPhone;
//    @ApiModelProperty("[必填] 用户ID")
    private Long userId;
    //token 验证
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkUserName() {
        return linkUserName;
    }

    public void setLinkUserName(String linkUserName) {
        this.linkUserName = linkUserName;
    }

    public Integer getLinkIdCardType() {
        return linkIdCardType;
    }

    public void setLinkIdCardType(Integer linkIdCardType) {
        this.linkIdCardType = linkIdCardType;
    }

    public String getLinkIdCard() {
        return linkIdCard;
    }

    public void setLinkIdCard(String linkIdCard) {
        this.linkIdCard = linkIdCard;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
