package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>用户视图信息</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserVO implements Serializable {
    private static final long serialVersionUID = -3170963254709671886L;
    private String userCode;                    //邮箱或者手机号
    private String userPassword;                //密码
    private String userName;                    //用户输入昵称

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
