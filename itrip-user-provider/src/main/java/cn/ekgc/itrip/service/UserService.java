package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.User;

public interface UserService {
    /**
     * <b>根据用户名校验该用户名被使用</b>
     *
     * @param name
     * @return true 则可以添加
     */
    boolean queryUserCodeIsCanUsed(String name) throws Exception;

    /**
     * <b>根据所给对象添加用户</b>
     *
     * @param entity
     * @return
     * @throws Exception
     */
    boolean saveUser(User entity) throws Exception;

    /**
     * <b>根据所给的手机号或者邮箱进行激活用户</b>
     *
     * @param userCode
     * @param code
     * @return
     * @throws Exception
     */
    boolean activateUser(String userCode, String code) throws Exception;

    /**
     * <b>根据所给用户信息查询用户</b>
     *
     * @param entity
     * @return
     * @throws Exception
     */
    User getUser(User entity) throws Exception;

    /**
     * <b>查询redis中存储的激活码</b>
     *
     * @param userCode
     * @return
     * @throws Exception
     */
    String getActivated(String userCode) throws Exception;

    /**
     * <b>进行激活码的发送</b>
     *
     * @param userCode
     * @throws Exception
     */
    void send(String userCode) throws Exception;

    /**
     * <b>用户登录</b>
     *
     * @param name
     * @param password
     * @return
     */
    ResultVO loginUser(String name, String password) throws Exception;
}
