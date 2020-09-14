package cn.ekgc.itrip.service;

public interface UserService {
    /**
     * <b>根据用户名校验该用户名被使用</b>
     * @param name
     * @return true 则可以添加
     */
    boolean queryUserCodeIsCanUsed(String name);
}
