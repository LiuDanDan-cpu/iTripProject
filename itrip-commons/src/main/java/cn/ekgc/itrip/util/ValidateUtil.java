package cn.ekgc.itrip.util;



/**
 * <b>用户信息校验工具类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidateUtil {
    private static String emailRegx = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static String cellphoneRegx ="^1[3|5|6|7|8|9][0-9]{9}$";

    /**
     * <b>校验Email 是否正确</b>
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        if (email!=null && email.matches(emailRegx)){
            return true;
        }
        return false;
    }

    /**
     * <b>校验登录密码</b>
     * @param password
     * @return
     */
    public static boolean checkPassword(String password){
        if (password!=null&&!"".equals(password)){
            return true;
        }
        return false;
    }

    /**
     * <b>校验手机号是否有效</b>
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone){
        if (cellphone!=null && cellphone.matches(cellphoneRegx)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkCellphone("17829164302"));
    }
}
