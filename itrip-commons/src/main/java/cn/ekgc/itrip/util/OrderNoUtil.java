package cn.ekgc.itrip.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>订单编号工具类</b>
 */
public class OrderNoUtil {
    public static String orderNo()throws Exception{
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        return simpleDateFormat.format(new Date());
    }
}
