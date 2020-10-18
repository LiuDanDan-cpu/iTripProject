package cn.ekgc.itrip.util;

import java.util.Properties;

/**
 * <b>支付宝常量工具类</b>
 */
public class TradeConstant {
    private static Properties prop=new Properties();
    static {
        try {
            prop.load(TradeConstant.class.getClassLoader().getResourceAsStream("props/Trade.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static final String URL=prop.getProperty("url");
    public static final String APPID=prop.getProperty("appId");
    public static final String APP_PRIVATE_KEY=prop.getProperty("app_private_key");
    public static final String FORMAT=prop.getProperty("format");
    public static final String CHARSET=prop.getProperty("CHARSET");
    public static final String ALIPAY_PUBLIC_KEY=prop.getProperty("ALIPAY_PUBLIC_KEY");
    public static final String SIGN_TYPE=prop.getProperty("SIGN_TYPE");
    /**
     * 销售产品码
     */
    public static final String PRODUCT_CODE=prop.getProperty("product_code");
}
