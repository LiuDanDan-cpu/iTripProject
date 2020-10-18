package cn.ekgc.itrip.util;


import cn.ekgc.itrip.enums.NumberNameEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>订单编号生成工具类</b>
 */
public class Number {
    /**
     * <b>生成订单编号</b>
     *
     * @param query
     * @return
     */
    //如果时酒店就给JD 如果是旅游
    public static String getNumber(Integer query) {
        String Bj = "";//标志
        if (query == 0) {
            Bj = "LY";
        } else if (query == 1) {
            Bj = "JD";
        } else if (query == 2) {
            Bj = "JP";
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        Bj = Bj + simpleDateFormat.format(new Date());
        return Bj;
    }

    public static void main(String[] args) {
        System.out.println(Number.getNumber(NumberNameEnum.NUMBER_NAME_ONE.getCode()));
    }
}
