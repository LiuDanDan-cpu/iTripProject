package cn.ekgc.itrip.util;


import java.util.Properties;

/**
 * <b>系统常量工具类</b>
 */
public class ConstantUtils {
    private static Properties prop=new Properties();
    static {
        try {
            prop.load(ConstantUtils.class.getClassLoader().getResourceAsStream("props/itrip.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * <b>token 保存时间</b>
     */
    public static final Long TOKEN_EXPRIE=Long.parseLong(prop.getProperty("token.expire"));
    /**
     *<b>收件人邮箱</b>
     */
    public static final String MAIL_FROM= prop.getProperty("mail.from");
    /**
     *<b>验证码的有效时间</b>
     */
    public static final String MAIL_EXPIRE= prop.getProperty("mail.expire");




    /**
     * <b>容联云短信验证码配置IP地址</b>
     */
    public static final String CLOOPEN_SERVER_IP= prop.getProperty("cloopen.server.ip");
    /**
     * <b>容联云短信验证码配置port端口号</b>
     */

    public static final String CLOOPEN_SERVER_PORT= prop.getProperty("cloopen.server.port");
    /**
     * <b>容联云短信验证码配置sid</b>
     */
    public static final String CLOOPEN_ACCOUNT_SID= prop.getProperty("cloopen.account.sid");
    /**
     * <b>容联云短信验证码配置token</b>
     */
    public static final String CLOOPEN_ACCOUNT_TOKEN= prop.getProperty("cloopen.account.token");
    /**
     * <b>容联云短信验证码配置app_id</b>
     */
    public static final String CLOOPEN_APP_ID= prop.getProperty("cloopen.app.id");
    /**
     * <b>容联云短信验证码配置template.id</b>
     */
    public static final String CLOOPEN_TEMPLATE_ID= prop.getProperty("cloopen.template.id");

}
