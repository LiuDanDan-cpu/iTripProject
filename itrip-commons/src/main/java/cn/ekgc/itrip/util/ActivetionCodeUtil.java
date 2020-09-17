package cn.ekgc.itrip.util;

import java.util.Random;

/**
 * <b>激活码生成工具类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ActivetionCodeUtil {
    /**
     * <b>生成6位激活码</b>
     * @return
     */
    public static String generate(){
        Random random=new Random();
        //使用string buffer 存储激活码信息
        StringBuffer sb=new StringBuffer();
        //循环6次
        while (sb.length()!=6){
            //生成十以内的6位激活码
            sb.append(random.nextInt(10));
        }
        //将生成的sb 激活码变为string字符串 然后返回
        return sb.toString();
    }
}
